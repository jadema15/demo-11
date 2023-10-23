package com.proyecto.dev.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.dev.dto.AlumnoDto;
import com.proyecto.dev.dto.EstadoDto;

@Repository
public interface IAlumnoRepository extends JpaRepository<AlumnoDto, Long> {

	List<AlumnoDto> findByEstado(EstadoDto estado);

	@Query(value = "SELECT * FROM alumnos WHERE nombre = :nombre", nativeQuery = true)
	AlumnoDto findByNombreNativo(String nombre);
	
	@Query(value = "SELECT * FROM alumnos WHERE nombre LIKE %:nombre%", nativeQuery = true)
	List<AlumnoDto> findByNombreContaining(String nombre);
	
	Page<AlumnoDto> findAll(Pageable pageable);

}
