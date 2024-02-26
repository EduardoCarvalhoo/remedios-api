package com.remedios.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.remedios.enums.Laboratorio;
import com.remedios.enums.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRemediosDTO(

		@NotBlank 
		String nome, 
		@Enumerated
		Via via,
		@NotBlank 
		String lote, 
		int quantidade,
		@Future 
		LocalDate validade, 
		@Enumerated
		Laboratorio laboratorio) implements Serializable {

}
