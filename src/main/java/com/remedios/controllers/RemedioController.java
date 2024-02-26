package com.remedios.controllers;

import java.util.List;

import com.remedios.constantes.RabbitmqConstantes;
import com.remedios.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.dtos.DadosAtualizarRemedioDTO;
import com.remedios.dtos.DadosCadastroRemediosDTO;
import com.remedios.dtos.DadosDetalhamentoRemedioDTO;
import com.remedios.dtos.DadosListagemRemedioDTO;
import com.remedios.domain.remedio.Remedio;
import com.remedios.repository.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;
	@Autowired
	private RabbitmqService rabbitmqService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedioDTO> cadastrar(@RequestBody @Valid DadosCadastroRemediosDTO dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);

		System.out.println(dados.quantidade());
		this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_REMEDIO, dados);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedioDTO(remedio));
	}

	@GetMapping
	public ResponseEntity<List<DadosListagemRemedioDTO>> listar() {
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedioDTO::new).toList();
		
		return ResponseEntity.ok(lista);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedioDTO> atualizar(@RequestBody @Valid DadosAtualizarRemedioDTO dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedioDTO(remedio));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping("reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.setAtivar();
		
		return ResponseEntity.noContent().build();

	}
}
