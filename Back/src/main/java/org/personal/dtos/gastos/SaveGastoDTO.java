package org.personal.dtos.gastos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.personal.models.TipoGasto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveGastoDTO {
	private TipoGasto tipoGasto;
	private Integer montoGasto;
}
