package org.personal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_gastos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoGastoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, name = "nombre_gasto")
	private String nombreGasto;
}
