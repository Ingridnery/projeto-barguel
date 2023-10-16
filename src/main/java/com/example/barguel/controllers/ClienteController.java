package com.example.barguel.controllers;

import com.example.barguel.models.aluguel.AluguelModel;
import com.example.barguel.models.cliente.ClienteModel;
import com.example.barguel.services.AluguelService;
import com.example.barguel.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.barguel.dtos.ClienteDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("cliente")
public class ClienteController {

    final ClienteService clienteService;
    final AluguelService aluguelService;

    public ClienteController(ClienteService clienteService, AluguelService aluguelService) {
        this.clienteService = clienteService;
        this.aluguelService = aluguelService;
    }
    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveCliente(@Valid @RequestBody ClienteDto clienteDto){
        var clienteModel= new ClienteModel();
        BeanUtils.copyProperties(clienteDto,clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if(clienteModelOptional.isPresent()){
            var clienteModel = clienteModelOptional.get();
            clienteModel.setCpf(clienteDto.getCpf());
            clienteModel.setEmail(clienteDto.getEmail());
            clienteModel.setNome(clienteDto.getNome());
            clienteModel.setArraisAmador(clienteDto.getArraisAmador());

            return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteService.findAll());
    }

    @GetMapping(value = "/findBy/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id")UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id")UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if(clienteModelOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        List<AluguelModel> alugueis =aluguelService.findAll()
                .stream()
                .filter(aluguel -> aluguel.getCliente().getId().equals(clienteModelOptional.get().getId()))
                .toList();
        if(alugueis.isEmpty()){
            clienteService.deleteByid(clienteModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não pode ser deletado pois possui um aluguel em aberto!");

    }
    @GetMapping(value = "getAll")
    public ResponseEntity<List<ClienteModel>> getAllClientes(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }


}
