package com.estudaMais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudaMais.entity.Aluno;
import com.estudaMais.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private final AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarAlunoId(@PathVariable Long id){
		Aluno aluno = alunoService.buscarAlunoPorId(id);
		if(aluno != null) {
			return ResponseEntity.ok(aluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Aluno>> buscarTodosAlunos(){
		List<Aluno> aluno = alunoService.buscarTodosAlunos();
		return ResponseEntity.ok(aluno);
	}
	
	@PostMapping("/")
	public ResponseEntity<Aluno> salvaAluno(@RequestBody Aluno aluno){
		Aluno saveAluno = alunoService.salvarAluno(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveAluno);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alteraAluno(@PathVariable Long id, @RequestBody Aluno aluno){
		Aluno atualizaAluno = alunoService.atualizarAluno(id, aluno);
		if(atualizaAluno != null) {
			return ResponseEntity.ok(atualizaAluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> apagaAluno(@PathVariable Long id){
		boolean apagaAluno = alunoService.apagarAluno(id);
		if(apagaAluno) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Aluno> authenticate(@RequestBody Aluno alunoDetails){
		Aluno authenticatedMail = alunoService.authenticate(alunoDetails.getEmail(), alunoDetails.getPassword());
		if(authenticatedMail != null) {
			authenticatedMail.setPassword(null);
			return ResponseEntity.ok(authenticatedMail);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
