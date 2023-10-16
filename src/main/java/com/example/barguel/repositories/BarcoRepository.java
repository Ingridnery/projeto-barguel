package com.example.barguel.repositories;

import com.example.barguel.models.barco.BarcoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarcoRepository extends JpaRepository<BarcoModel, UUID> {
}