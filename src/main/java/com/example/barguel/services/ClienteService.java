package com.example.barguel.services;

import com.example.barguel.models.cliente.ClienteModel;
import com.example.barguel.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteModel save(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);}

    public List<ClienteModel> findAll(){return clienteRepository.findAll();}
    @Transactional
    public void deleteByid(UUID id){clienteRepository.deleteById(id);}

    @Transactional
    public void delete(ClienteModel clienteModel){deleteByid(clienteModel.getId());}

    public Optional<ClienteModel> findById(UUID id){return  clienteRepository.findById(id);}
}
