package com.proyecto.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dev.commons.MappingConstants;
import com.proyecto.dev.dto.AlumnoDto;
import com.proyecto.dev.Vo.AlumnosAge;
import com.proyecto.dev.serviceImpl.AlumnoServiceImpl;
import com.proyecto.dev.serviceImpl.JWTUtilService;

@RestController
@RequestMapping("/api")
public class AlumnoController {

	@Autowired
	private AlumnoServiceImpl alumnoServiceImpl;

	@Autowired
	private JWTUtilService jwtUtilService;

	@GetMapping(value = MappingConstants.ALUMNOS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> getAlumnosAll(@RequestHeader(value = "Authorization") String token) {

		if (!jwtUtilService.ValidarToken(token)) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			List<AlumnoDto> listAlumnos = alumnoServiceImpl.getListAlumno();
			if (!listAlumnos.isEmpty()) {
				return new ResponseEntity<>(listAlumnos, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS + MappingConstants.ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> getAlumnos(@RequestHeader(value = "Authorization") String token,
			@PathVariable Long id) {
		if (!jwtUtilService.ValidarToken(token)) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			if (id == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			AlumnoDto alumno = alumnoServiceImpl.getAlumnoById(id);
			if (alumno != null) {
				return new ResponseEntity<>(alumno, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = MappingConstants.ALUMNOS + MappingConstants.ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> updateAlumnos(@RequestHeader(value = "Authorization") String token,
			@PathVariable Long id, @RequestBody AlumnoDto alumno) {
		AlumnoDto alumnoRespuesta = new AlumnoDto();
		try {
			alumnoRespuesta = alumnoServiceImpl.updateAlumno(id, alumno);
			if (alumnoRespuesta != null) {
				return new ResponseEntity<>(alumnoRespuesta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = MappingConstants.ALUMNOS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> saveAlumnos(@RequestHeader(value = "Authorization") String token,
			@RequestBody AlumnoDto alumno) {
		AlumnoDto alumnoRespuesta = new AlumnoDto();
		try {
			alumnoRespuesta = alumnoServiceImpl.saveAlumno(alumno);
			if (alumnoRespuesta != null) {
				return new ResponseEntity<>(alumnoRespuesta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS
			+ MappingConstants.LIST_AGE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnosAge>> getListAlumnosAge(@RequestHeader(value = "Authorization") String token) {
		List<AlumnosAge> listAlumnos = alumnoServiceImpl.getListAlumnoAge();
		try {
			if (!listAlumnos.isEmpty()) {
				return new ResponseEntity<>(listAlumnos, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS + MappingConstants.PRUEBA, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getPrueba() {
		try {
			String cadena = alumnoServiceImpl.salidaOtra();
			return new ResponseEntity<>(cadena, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS
			+ MappingConstants.FIZZ_BUZZ, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getFizzBuzz() {
		try {
			String cadena = alumnoServiceImpl.getFizzBuzz();
			return new ResponseEntity<>(cadena, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS
			+ MappingConstants.INTEGRATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getIntegration() {
		try {
			String cadena = alumnoServiceImpl.integracion2();
			return new ResponseEntity<>(cadena, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS
			+ MappingConstants.EXECPCION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getExecpcion() {
		try {
			String cadena = alumnoServiceImpl.getExecpcion();
			return new ResponseEntity<>(cadena, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS + MappingConstants.ESTADO_ACTIVO
			+ MappingConstants.ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> getAlumnoEstado(@PathVariable Long id) {
		try {
			List<AlumnoDto> alumno = alumnoServiceImpl.getAlumnoEstado(id);
			return new ResponseEntity<>(alumno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = MappingConstants.ALUMNOS + MappingConstants.FLECHA, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> getAlumnoFlecha() {
		try {
			List<AlumnoDto> alumno = alumnoServiceImpl.getAlumnoFlecha();
			return new ResponseEntity<>(alumno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
