package org.personal.services.impl;

import org.modelmapper.ModelMapper;
import org.personal.dtos.gastos.ResponseGastoDTO;
import org.personal.dtos.gastos.SaveGastoDTO;
import org.personal.entities.GastoEntity;
import org.personal.entities.TipoGastoEntity;
import org.personal.models.TipoGasto;
import org.personal.repositories.GastoJpaRepository;
import org.personal.repositories.TipoGastoJpaRepository;
import org.personal.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GastoServiceImpl implements GastoService {
	@Autowired
	private GastoJpaRepository gastoJpaRepository;
	@Autowired
	private TipoGastoJpaRepository tipoGastoJpaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ResponseGastoDTO> getGastoList() {
		List<GastoEntity> gastoEntities = gastoJpaRepository.findAll();
		List<ResponseGastoDTO> gastoList = new ArrayList<>();

		for (GastoEntity gastoEntity : gastoEntities) {
			ResponseGastoDTO responseGastoDTO = modelMapper.map(gastoEntity, ResponseGastoDTO.class);
			responseGastoDTO.setNombreGasto(gastoEntity.getTipoGasto().getNombreGasto());
			gastoList.add(responseGastoDTO);
		}
		return gastoList;
	}

	@Override
	public ResponseGastoDTO getGastoById(Long id) {
		GastoEntity gastoEntity = gastoJpaRepository.getReferenceById(id);
		ResponseGastoDTO responseGastoDTO = modelMapper.map(gastoEntity, ResponseGastoDTO.class);
		responseGastoDTO.setNombreGasto(gastoEntity.getTipoGasto().getNombreGasto());
		return responseGastoDTO;
	}

	@Override
	public ResponseGastoDTO createGasto(SaveGastoDTO gasto) throws IllegalArgumentException {
		GastoEntity gastoEntity = modelMapper.map(gasto, GastoEntity.class);
		gastoEntity.setFechaGasto(LocalDate.now());
		gastoEntity.setTipoGasto(modelMapper.map(gasto.getTipoGasto(), TipoGastoEntity.class));
		GastoEntity gastoEntitySaved = gastoJpaRepository.save(gastoEntity);

		ResponseGastoDTO responseGastoDTO = modelMapper.map(gastoEntitySaved, ResponseGastoDTO.class);
		responseGastoDTO.setNombreGasto(gastoEntitySaved.getTipoGasto().getNombreGasto());

		return responseGastoDTO;
	}

	@Override
	public ResponseGastoDTO updateGasto(Long id, SaveGastoDTO gasto) {
		GastoEntity gastoEntity = gastoJpaRepository.getReferenceById(id);
		gastoEntity.setMontoGasto(gasto.getMontoGasto());
		gastoEntity.setTipoGasto(modelMapper.map(gasto.getTipoGasto(), TipoGastoEntity.class));
		GastoEntity gastoEntitySaved = gastoJpaRepository.save(gastoEntity);
		ResponseGastoDTO responseGastoDTO = modelMapper.map(gastoEntitySaved, ResponseGastoDTO.class);
		responseGastoDTO.setNombreGasto(gastoEntitySaved.getTipoGasto().getNombreGasto());

		return responseGastoDTO;
	}

	@Override
	public void deleteGastoById(Long id) {
		gastoJpaRepository.deleteById(id);
	}

	@Override
	public List<TipoGasto> getAllTiposGasto() {
		List<TipoGastoEntity> gastoEntities = tipoGastoJpaRepository.findAll();
		List<TipoGasto> gastoList = new ArrayList<>();
		for (TipoGastoEntity tipoGastoEntity : gastoEntities) {
			gastoList.add(modelMapper.map(tipoGastoEntity, TipoGasto.class));
		}
		return gastoList;
	}

}
