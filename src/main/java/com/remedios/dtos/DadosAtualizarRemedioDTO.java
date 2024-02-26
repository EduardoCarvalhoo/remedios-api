package com.remedios.dtos;

import com.remedios.enums.Laboratorio;
import com.remedios.enums.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedioDTO(
		
		@NotNull
		Long id, 
		String nome, 
		Via via,
		Laboratorio laboratorio) {
	
}
