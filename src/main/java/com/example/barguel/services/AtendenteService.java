package com.example.barguel.services;

import com.example.barguel.models.atendente.AtendenteModel;
import com.example.barguel.repositories.AtendenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AtendenteService {
    final AtendenteRepository atendenteRepository;

    public AtendenteService(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    @Transactional
    public AtendenteModel save(AtendenteModel atendenteModel){
        return atendenteRepository.save(atendenteModel);
    }

    public Optional<AtendenteModel> findByUsername(String username) {
        return atendenteRepository.findAll().stream()
                .filter(atendente -> atendente.getUsername().equals(username))
                .findFirst();
    }

    public boolean checkIfExists(String username) {
        return findByUsername(username).isPresent();
    }

}
