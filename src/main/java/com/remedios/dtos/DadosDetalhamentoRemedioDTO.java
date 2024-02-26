package com.remedios.dtos;

import com.remedios.domain.remedio.Remedio;
import com.remedios.enums.Laboratorio;
import com.remedios.enums.Via;

import java.time.LocalDate;

public record DadosDetalhamentoRemedioDTO(Long id, String nome, Via via, String lote, int quantidade, LocalDate validade,
										  Laboratorio laboratorio, Boolean ativo) {

	public DadosDetalhamentoRemedioDTO(Remedio remedio) {
		this(
				remedio.getId(),
				remedio.getNome(),
				remedio.getVia(),
				remedio.getLote(),
				remedio.getQuantidade(),
				remedio.getValidade(),
				remedio.getLaboratorio(),
				remedio.getAtivo());
	}
}
