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


    @GetMapping("/ativos")
    public ResponseEntity<List<Curso>> buscarCursosAtivos() {
        return ResponseEntity.ok(cursoService.buscarCursosAtivos());
    }

    @GetMapping("/destaques")
    public ResponseEntity<List<Curso>> buscarDestaques() {
        return ResponseEntity.ok(cursoService.buscarDestaques());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorTitulo(@RequestParam String termo) {
        return ResponseEntity.ok(cursoService.buscarPorTitulo(termo));
    }

    @PostMapping("/{id}/acesso")
    public ResponseEntity<Void> registrarAcesso(@PathVariable Long id) {
        cursoService.incrementarAcessos(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Curso>> listarCursosDoProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(cursoService.listarCursosDoProfessor(professorId));
    }

    @PostMapping("/professor")
    public ResponseEntity<Curso> criarCursoDoProfessor(@RequestBody Curso curso,
                                                       @RequestParam Long professorId) {
        Curso cursoSalvo = cursoService.salvarCursoDoProfessor(curso, professorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

    @PutMapping("/professor/{id}")
    public ResponseEntity<Curso> atualizarCursoDoProfessor(@PathVariable Long id,
                                                           @RequestBody Curso curso,
                                                           @RequestParam Long professorId) {
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        return cursoAtualizado != null 
                ? ResponseEntity.ok(cursoAtualizado) 
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/professor/{id}")
    public ResponseEntity<Void> apagarCursoDoProfessor(@PathVariable Long id,
                                                        @RequestParam Long professorId) {
        Boolean apagado = cursoService.apagarCurso(id);
        return apagado 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/")
    public ResponseEntity<List<Curso>> buscarTodosCursos() {
        return ResponseEntity.ok(cursoService.buscarTodosCursos());
    }

    @PostMapping("/")
    public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.salvarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        return cursoAtualizado != null 
                ? ResponseEntity.ok(cursoAtualizado) 
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCurso(@PathVariable Long id) {
        Boolean apagado = cursoService.apagarCurso(id);
        return apagado 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.notFound().build();
    }
}
