package com.proyecto.dev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dev.Vo.UsuarioVo;
import com.proyecto.dev.commons.MappingConstants;
import com.proyecto.dev.serviceImpl.JWTUtilService;

@RestController
@RequestMapping("/api")
public class Login {

	@Autowired
	private JWTUtilService jwtUtilService;

	@PostMapping(value = MappingConstants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody UsuarioVo usuarioVo) {
		try {
			String salida = jwtUtilService.ValidarUsuario(usuarioVo);
			if (salida != null) {
				return new ResponseEntity<>(salida, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
