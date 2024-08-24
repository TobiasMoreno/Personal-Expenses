package org.personal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.personal.models.TipoGasto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "tipo_gasto_id", nullable = false)
	private TipoGastoEntity tipoGasto;
	@Column(nullable = false, name = "monto_gasto")
	private Integer montoGasto;
	@Column(nullable = false, name = "fecha_gasto")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaGasto;
}
