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

import com.estudaMais.entity.Professor;
import com.estudaMais.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Professor> buscarProfessorId(@PathVariable Long id){
		Professor professor = professorService.buscarProfessorPorId(id);
		if(professor != null) {
			return ResponseEntity.ok(professor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Professor>> buscarTodosProfessors(){
		List<Professor> professor = professorService.buscarTodosProfessors();
		return ResponseEntity.ok(professor);
	}
	
	@PostMapping("/")
	public ResponseEntity<Professor> salvaProfessor(@RequestBody Professor professor){
		Professor saveProfessor = professorService.salvarProfessor(professor);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProfessor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Professor> alteraProfessor(@PathVariable Long id, @RequestBody Professor professor){
		Professor atualizaProfessor = professorService.atualizarProfessor(id, professor);
		if(atualizaProfessor != null) {
			return ResponseEntity.ok(atualizaProfessor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Professor> apagaProfessor(@PathVariable Long id){
		boolean apagaProfessor = professorService.apagarProfessor(id);
		if(apagaProfessor) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Professor> authenticate(@RequestBody Professor professorDetails){
		Professor authenticatedMail = professorService.authenticate(professorDetails.getEmail(), professorDetails.getPassword());
		if(authenticatedMail != null) {
			authenticatedMail.setPassword(null);
			return ResponseEntity.ok(authenticatedMail);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
