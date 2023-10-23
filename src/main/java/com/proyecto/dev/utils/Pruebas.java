package com.proyecto.dev.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyecto.dev.dto.EstadoDto;

@Configuration
public class Pruebas {

	@Bean
	public EstadoDto consultaEstado() {
		return new EstadoDto(3L, "Inactivo");
	}

}
