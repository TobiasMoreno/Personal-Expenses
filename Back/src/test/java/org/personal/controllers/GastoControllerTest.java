package org.personal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.personal.dtos.gastos.ResponseGastoDTO;
import org.personal.dtos.gastos.SaveGastoDTO;
import org.personal.models.Gasto;
import org.personal.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GastoController.class)
class GastoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GastoService gastoService;

	@Test
	void getGastoList() throws Exception {
		ResponseGastoDTO firstGasto = new ResponseGastoDTO();
		ResponseGastoDTO secondGasto = new ResponseGastoDTO();

		firstGasto.setNombreGasto("firstGasto");
		secondGasto.setNombreGasto("secondGasto");

		List<ResponseGastoDTO> gastoList = new ArrayList<>();
		gastoList.add(firstGasto);
		gastoList.add(secondGasto);

		when(gastoService.getGastoList()).thenReturn(gastoList);

		mockMvc.perform(get("/gasto"))
				.andDo(print())
				.andExpect(status().isOk())
				// First element
				.andExpect(jsonPath("$[0].gasto").value("firstGasto"))
				// Second element
				.andExpect(jsonPath("$[1].gasto").value("secondGasto"));
	}

	@Test
	void getGastoById() throws Exception {
		ResponseGastoDTO gastoToReturn = new ResponseGastoDTO();
		gastoToReturn.setNombreGasto("something");

		when(gastoService.getGastoById(1L)).thenReturn(gastoToReturn);

		// First way to test
		mockMvc.perform(get("/gasto/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombreGasto").value("something"));
		//.andExpect(jsonPath("$.anotherAttribute").value("attribute value"));
		//.andExpect(jsonPath("$.anotherAttribute").value("attribute value"));

		// Second way to test
		MvcResult mvcResult = mockMvc.perform(get("/gasto/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Gasto gastoResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Gasto.class);

		assertEquals("something", gastoResult.getNombreGasto());
		// assertEquals("attribute value", gastoResult.getAnotherAttribute());
		// assertEquals("attribute value", gastoResult.getAnotherAttribute());
	}

	@Test
	void createGasto() throws Exception {
		SaveGastoDTO gastoToCreate = new SaveGastoDTO();
		gastoToCreate.setNombreGasto("gastoToCreate");

		ResponseGastoDTO gastoCreated = new ResponseGastoDTO();
		gastoCreated.setNombreGasto("createdGasto");

		when(gastoService.createGasto(any(SaveGastoDTO.class))).thenReturn(gastoCreated);

		mockMvc.perform(post("/gasto")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(gastoToCreate)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.gasto").value("createdGasto"));
	}

	@Test
	void createGastoWithWrongGasto() throws Exception {
		SaveGastoDTO gastoToCreate = new SaveGastoDTO();
		gastoToCreate.setNombreGasto("gastoToCreate");

		when(gastoService.createGasto(any(SaveGastoDTO.class))).thenThrow(new IllegalArgumentException("Gasto already exists"));

		mockMvc.perform(post("/gasto")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(gastoToCreate)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Bad Request"))
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.message").value("Gasto already exists"));
	}

	@Test
	void updateGasto() throws Exception {
		SaveGastoDTO gastoToUpdate = new SaveGastoDTO();
		gastoToUpdate.setNombreGasto("gastoToUpdate");

		ResponseGastoDTO gastoUpdated = new ResponseGastoDTO();
		gastoUpdated.setNombreGasto("updatedGasto");

		when(gastoService.updateGasto(any(Long.class), any(SaveGastoDTO.class))).thenReturn(gastoUpdated);

		mockMvc.perform(put("/gasto/1")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(gastoToUpdate)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.gasto").value("updatedGasto"));
	}

	@Test
	void deleteGastoById() throws Exception {
		mockMvc.perform(delete("/gasto/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}