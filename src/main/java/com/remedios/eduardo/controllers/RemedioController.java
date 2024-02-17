package com.remedios.eduardo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.eduardo.remedio.DadosCadastroRemedios;
import com.remedios.eduardo.remedio.Remedio;
import com.remedios.eduardo.remedio.RemedioRepository;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;

	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroRemedios dados) {
		repository.save(new Remedio(dados));
	}
}
