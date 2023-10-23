package com.proyecto.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.dev.dto.AlumnoRolDto;

public interface AlumnoRolRepository extends JpaRepository<AlumnoRolDto, Long> {

	@Query(value = "SELECT * FROM alumno_rol WHERE alumno_id = :id", nativeQuery = true)
	List<AlumnoRolDto> findByAlumnoId(Long id);

}
