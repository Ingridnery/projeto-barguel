package com.example.barguel.controllers;

import com.example.barguel.dtos.AtendenteDto;
import com.example.barguel.models.atendente.AtendenteModel;
import com.example.barguel.services.AtendenteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("atendente")
public class AtendenteController {
    final AtendenteService atendenteService;

    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> saveAtendente(@Valid @RequestBody AtendenteDto atendenteDto){
        var atendenteModel = new AtendenteModel();
        BeanUtils.copyProperties(atendenteDto, atendenteModel);

//        if(atendenteService.checkIfExists(atendenteModel.getUsername()))
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteService.save(atendenteModel));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AtendenteDto atendenteDto){
        AtendenteModel inDBAtendente = atendenteService.findByUsername(atendenteDto.getUsername()).get();
        if(inDBAtendente.getPassword().equals(atendenteDto.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body(inDBAtendente.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Senha incorreta");
    }
}
