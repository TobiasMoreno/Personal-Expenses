package org.personal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gasto {
	private Long id;
	private TipoGasto nombreGasto;
	private Integer montoGasto;
	private LocalDate fechaGasto;
}
