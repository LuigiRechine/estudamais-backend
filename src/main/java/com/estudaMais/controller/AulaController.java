package com.estudaMais.controller;

import java.util.List;

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

import com.estudaMais.entity.Aula;
import com.estudaMais.service.AulaService;

@RestController
@RequestMapping("/aulas") 
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping("/curso/{cursoId}")
    public ResponseEntity<Aula> criar(@PathVariable Long cursoId, @RequestBody Aula aula) {
        Aula novaAula = aulaService.criarAula(cursoId, aula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAula);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Aula>> listarPorCurso(@PathVariable Long cursoId) {
        List<Aula> aulas = aulaService.listarPorCurso(cursoId);
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Long id) {
        Aula aula = aulaService.buscarPorId(id);
        return ResponseEntity.ok(aula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizar(@PathVariable Long id, @RequestBody Aula aulaAtualizado) {
        Aula aula = aulaService.atualizarAula(id, aulaAtualizado);
        if (aula != null) {
            return ResponseEntity.ok(aula);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Boolean deletado = aulaService.apagarAula(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
