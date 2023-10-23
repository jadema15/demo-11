package com.proyecto.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.dev.dto.EstadoDto;

@Repository
public interface IEstadoRepository extends JpaRepository<EstadoDto, Long> {

}
