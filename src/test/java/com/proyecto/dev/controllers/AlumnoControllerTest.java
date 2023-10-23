package com.proyecto.dev.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.dev.dto.AlumnoDto;
import com.proyecto.dev.dto.EstadoDto;
import com.proyecto.dev.repository.IAlumnoRepository;
import com.proyecto.dev.serviceImpl.AlumnoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTest {

	@Mock
	private IAlumnoRepository iAlumnoRepositoryMock;

	@InjectMocks
	private AlumnoServiceImpl alumnoServiceImplMock;

	@Test
	public void AlumnoListTest() {

		Date fecha = new Date();
		EstadoDto estadoDto = new EstadoDto();

		List<AlumnoDto> alulmnosLista = Arrays.asList(new AlumnoDto("jairo", "delgado", fecha, estadoDto, null));

		when(alumnoServiceImplMock.getListAlumno()).thenReturn(alulmnosLista);

		List<AlumnoDto> resultado = alumnoServiceImplMock.getListAlumno();

		assertNotNull(resultado);
		assertEquals(alulmnosLista, resultado);
	}

}
