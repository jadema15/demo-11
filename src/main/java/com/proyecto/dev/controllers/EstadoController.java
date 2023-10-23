package com.proyecto.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dev.dto.EstadoDto;
import com.proyecto.dev.serviceImpl.EstadoServiceImpl;

@RestController
@RequestMapping("/api")
public class EstadoController {

	@Autowired
	EstadoServiceImpl estadoServiceImpl;

	@GetMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstadoDto>> getAlumnosAll() {
		try {
			List<EstadoDto> listEstados = estadoServiceImpl.getListEstado();
			if (!listEstados.isEmpty()) {
				return new ResponseEntity<>(listEstados, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/estados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstadoDto> getAlumnos(@PathVariable Long id) {
		try {
			EstadoDto estado = estadoServiceImpl.getEstado(id);
			if (estado != null) {
				return new ResponseEntity<>(estado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
