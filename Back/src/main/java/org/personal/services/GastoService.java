package org.personal.services;

import org.personal.dtos.gastos.ResponseGastoDTO;
import org.personal.dtos.gastos.SaveGastoDTO;
import org.personal.models.TipoGasto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GastoService {
	List<ResponseGastoDTO> getGastoList();
	ResponseGastoDTO getGastoById(Long id);
	ResponseGastoDTO createGasto(SaveGastoDTO gasto) throws IllegalArgumentException;
	ResponseGastoDTO updateGasto(Long id, SaveGastoDTO gasto);
	void deleteGastoById(Long id);
	List<TipoGasto> getAllTiposGasto();
}
