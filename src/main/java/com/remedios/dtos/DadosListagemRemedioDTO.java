package com.remedios.dtos;

import com.remedios.domain.remedio.Remedio;
import com.remedios.enums.Laboratorio;
import com.remedios.enums.Via;

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
