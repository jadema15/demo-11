package com.proyecto.dev.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dev.dto.EstadoDto;
import com.proyecto.dev.repository.IEstadoRepository;
import com.proyecto.dev.services.IEstadoService;

@Service
public class EstadoServiceImpl implements IEstadoService {

	@Autowired
	IEstadoRepository iEstadoRepository;

	@Override
	public EstadoDto getEstado(Long id) {
		return iEstadoRepository.findById(id).orElse(null);
	}

	@Override
	public List<EstadoDto> getListEstado() {
		return iEstadoRepository.findAll();
	}

}
