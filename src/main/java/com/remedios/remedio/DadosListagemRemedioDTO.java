package com.remedios.remedio;

import java.time.LocalDate;

public record DadosListagemRemedioDTO(
		Long id,
		String nome, 
		Via via, 
		String lote, 
		Laboratorio laboratorio, 
		LocalDate validade) {

	public DadosListagemRemedioDTO(Remedio remedio) {
		this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
	}
}
