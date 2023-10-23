package com.proyecto.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.dev.dto.RolDto;

public interface IRolRepository extends JpaRepository<RolDto, Long> {

}
