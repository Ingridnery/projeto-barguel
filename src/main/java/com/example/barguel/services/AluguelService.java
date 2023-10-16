package com.example.barguel.services;

import com.example.barguel.models.aluguel.AluguelModel;
import com.example.barguel.repositories.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AluguelService {
    final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    @Transactional
    public AluguelModel save(AluguelModel aluguelModel){
        return aluguelRepository.save(aluguelModel);
    }
    public boolean isValidDate(LocalDate dataInicio, LocalDate dataFim) {
        return !dataInicio.isBefore(LocalDate.now()) && !dataFim.isBefore(dataInicio);
    }


    public List<AluguelModel> findAll(){return aluguelRepository.findAll();}


    @Transactional
    public void deleteByid(UUID id){
        aluguelRepository.deleteById(id);}

    public Optional<AluguelModel> findById(UUID id) { return aluguelRepository.findById(id);
    }
}
