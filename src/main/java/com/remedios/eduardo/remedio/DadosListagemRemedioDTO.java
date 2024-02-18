package com.remedios.eduardo.remedio;

import java.time.LocalDate;

public record DadosListagemRemedioDTO(
		
		String nome, 
		Via via, 
		String lote, 
		Laboratorio laboratorio, 
		LocalDate validade) {

	public DadosListagemRemedioDTO(Remedio remedio) {
		this(remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
	}
}
