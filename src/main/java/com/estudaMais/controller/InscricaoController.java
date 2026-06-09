package com.estudaMais.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudaMais.entity.Inscricao;
import com.estudaMais.service.InscricaoService;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    // Matricular aluno em um curso
    @PostMapping("/matricular")
    public ResponseEntity<Inscricao> matricular(@RequestParam Long alunoId,
                                                 @RequestParam Long cursoId) {
        Inscricao inscricao = inscricaoService.matricularAluno(alunoId, cursoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricao);
    }

    // Listar matriculas de um aluno
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Inscricao>> listarDoAluno(@PathVariable Long alunoId) {
        List<Inscricao> inscricoes = inscricaoService.listarMatriculasDoAluno(alunoId);
        return ResponseEntity.ok(inscricoes);
    }

    // Cancelar matrícula
    @DeleteMapping("/cancelar")
    public ResponseEntity<Void> cancelar(@RequestParam Long alunoId,
                                          @RequestParam Long cursoId) {
        inscricaoService.cancelarMatricula(alunoId, cursoId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/professor/{professorId}/alunos-ativos")
    public ResponseEntity<Map<String, Integer>> contarAlunosAtivos(@PathVariable Long professorId) {
        int total = inscricaoService.contarAlunosAtivosPorProfessor(professorId);
        return ResponseEntity.ok(Map.of("totalAlunosAtivos", total));
    }
}