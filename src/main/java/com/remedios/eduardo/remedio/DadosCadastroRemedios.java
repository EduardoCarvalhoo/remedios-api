package com.remedios.eduardo.remedio;

public record DadosCadastroRemedios(
		String nome,
		Via via,
		String lote,
		String quantidade,
		String validade,
		Laboratorio laboratorio) {

}
