package com.proyecto.dev.services;

import java.util.List;

import com.proyecto.dev.dto.EstadoDto;

public interface IEstadoService {

	public EstadoDto getEstado(Long id);

	public List<EstadoDto> getListEstado();

}
