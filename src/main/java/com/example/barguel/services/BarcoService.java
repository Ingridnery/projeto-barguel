package com.example.barguel.services;

import com.example.barguel.models.barco.BarcoModel;
import com.example.barguel.repositories.BarcoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarcoService {
    final BarcoRepository barcoRepository;

    public BarcoService(BarcoRepository barcoRepository) {
        this.barcoRepository = barcoRepository;
    }

    @Transactional
    public BarcoModel save(BarcoModel barcoModel){
        return barcoRepository.save(barcoModel);
    }

    public List<BarcoModel> findAll(){return barcoRepository.findAll();}
    @Transactional
    public void deleteByid(UUID id){barcoRepository.deleteById(id);}

    @Transactional
    public void delete(BarcoModel barcoModel){deleteByid(barcoModel.getId());}

    public Optional<BarcoModel> findById(UUID id){return barcoRepository.findById(id);}
}
