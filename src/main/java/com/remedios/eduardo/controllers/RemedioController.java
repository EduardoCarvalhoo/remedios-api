package com.remedios.eduardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.eduardo.remedio.DadosCadastroRemedios;
import com.remedios.eduardo.remedio.DadosListagemRemedioDTO;
import com.remedios.eduardo.remedio.Remedio;
import com.remedios.eduardo.remedio.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedios dados) {
		repository.save(new Remedio(dados));
	}
	
	@GetMapping
	public List<DadosListagemRemedioDTO> listar(){
		return repository.findAll().stream().map(DadosListagemRemedioDTO::new).toList();
	}
}


