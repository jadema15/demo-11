package com.proyecto.dev.services;

import java.util.List;

import com.proyecto.dev.dto.AlumnoDto;

public interface IAlumnoService {

	public AlumnoDto getAlumnoById(Long id);

	public List<AlumnoDto> getListAlumno();

	public AlumnoDto saveAlumno(AlumnoDto alumno);

	public AlumnoDto updateAlumno(Long id, AlumnoDto alumno);

}
