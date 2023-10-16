package com.example.barguel.repositories;

import com.example.barguel.models.aluguel.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AluguelRepository extends JpaRepository<AluguelModel, UUID> {
}