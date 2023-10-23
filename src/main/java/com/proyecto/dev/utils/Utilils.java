package com.proyecto.dev.utils;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.proyecto.dev.serviceImpl.AlumnoServiceImpl;

@Component
public class Utilils {

	public Utilils() {
	}

	private static final Logger logger = LogManager.getLogger(AlumnoServiceImpl.class);

	public String Mensaje() {
		return "hola";
	}

	public Integer calculateAge(Date fechaNacimiento) {
		Date fechaActual = new Date();
		try {
			if (fechaNacimiento != null) {
				long diferenciaEnMillis = fechaActual.getTime() - fechaNacimiento.getTime();
				long edadEnAnios = diferenciaEnMillis / (1000L * 60 * 60 * 24 * 365);
				if (edadEnAnios >= 0) {
					return (int) edadEnAnios;
				}
			}
		} catch (Exception e) {
			logger.error("Error al calcular la edad " + e.getMessage());
		}
		return 0;
	}

}
