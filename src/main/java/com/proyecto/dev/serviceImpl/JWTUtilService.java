package com.proyecto.dev.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dev.Vo.UsuarioVo;
import com.proyecto.dev.dto.AlumnoDto;
import com.proyecto.dev.dto.AlumnoRolDto;
import com.proyecto.dev.repository.AlumnoRolRepository;
import com.proyecto.dev.utils.JWTUtil;
import java.util.Base64;

@Service
public class JWTUtilService {

	private static final Logger logger = LogManager.getLogger(AlumnoServiceImpl.class);

	@Autowired
	JWTUtil jwtutil;

	@Autowired
	AlumnoServiceImpl alumnoServiceImpl;

	@Autowired
	AlumnoRolRepository alumnoRolRepository;

	public String ValidarUsuario(UsuarioVo usuarioVo) {
		
		String textoEnBase64 = Base64.getEncoder().encodeToString(usuarioVo.getNombreUsuario().getBytes());
		System.out.println("texto propuesto: "+ textoEnBase64);		
		byte[] bytesDecodificados = Base64.getDecoder().decode(textoEnBase64.getBytes());
		String textoDecodificado = new String(bytesDecodificados);		
		System.out.println("texto decodificado: "+ textoDecodificado);
		
		AlumnoDto alumnoRespuesta = alumnoServiceImpl.getNombre(usuarioVo.getNombreUsuario());
		if (usuarioVo.getPassword().equals(alumnoRespuesta.getPassword())) {
			logger.error("Contraseñas No coinciden.");
			List<AlumnoRolDto> alumnoRolDto = alumnoRolRepository.findByAlumnoId(alumnoRespuesta.getId());
			return jwtutil.create(String.valueOf(alumnoRespuesta.getId()), alumnoRespuesta.getApellido(), alumnoRolDto);
		}
		return null;
	}

	public Boolean ValidarToken(String token) {
		String usuarioId = jwtutil.getKey(token);
		Long usuario = Long.parseLong(usuarioId);
		AlumnoDto alumno = alumnoServiceImpl.getAlumnoById(usuario);
		return alumno != null;
	}
}
