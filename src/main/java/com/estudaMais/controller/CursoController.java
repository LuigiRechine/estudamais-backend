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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudaMais.entity.Curso;
import com.estudaMais.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Curso>> buscarTodosCursos() {
        List<Curso> cursos = cursoService.buscarTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Curso>> buscarCursosAtivos() {
        List<Curso> cursos = cursoService.buscarCursosAtivos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/destaques")
    public ResponseEntity<List<Curso>> buscarDestaques() {
        List<Curso> destaques = cursoService.buscarDestaques();
        return ResponseEntity.ok(destaques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.salvarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        if (cursoAtualizado != null) {
            return ResponseEntity.ok(cursoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCurso(@PathVariable Long id) {
        Boolean apagado = cursoService.apagarCurso(id);
        if (apagado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/acesso")
    public ResponseEntity<Void> registrarAcesso(@PathVariable Long id) {
        cursoService.incrementarAcessos(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorTitulo(@RequestParam String termo) {
        List<Curso> cursos = cursoService.buscarPorTitulo(termo);
        return ResponseEntity.ok(cursos);
    }
    
}
