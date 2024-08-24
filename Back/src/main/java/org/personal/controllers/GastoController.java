package org.personal.controllers;

import java.util.List;


import org.personal.dtos.gastos.ResponseGastoDTO;
import org.personal.dtos.gastos.SaveGastoDTO;
import org.personal.models.TipoGasto;
import org.personal.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/gasto")
public class GastoController {
	@Autowired
	private GastoService gastoService;
	@GetMapping("/all")
	public ResponseEntity<List<ResponseGastoDTO>> getGastoList() {
		return ResponseEntity.ok(gastoService.getGastoList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseGastoDTO> getGastoById(@PathVariable Long id) {
		return ResponseEntity.ok(gastoService.getGastoById(id));
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseGastoDTO> createGasto(@RequestBody SaveGastoDTO saveGastoDTO) {
		return ResponseEntity.ok(gastoService.createGasto(saveGastoDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseGastoDTO> updateGasto(@PathVariable Long id, @RequestBody SaveGastoDTO saveGastoDTO) {
		return ResponseEntity.ok(gastoService.updateGasto(id, saveGastoDTO));
	}

	@DeleteMapping("/{id}")
	public void deleteGastoById(@PathVariable Long id) {
		gastoService.deleteGastoById(id);
	}

	@GetMapping("/nombres-gasto")
	public List<TipoGasto> obtenerNombresGasto() {
		return gastoService.getAllTiposGasto();
	}
}
