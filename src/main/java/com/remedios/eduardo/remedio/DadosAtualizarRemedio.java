package com.remedios.eduardo.remedio;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
		
		@NotNull
		Long id, 
		String nome, 
		Via via, 
		Laboratorio laboratorio) {
	
}
