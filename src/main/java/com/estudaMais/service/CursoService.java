package com.estudaMais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.estudaMais.entity.Curso;
import com.estudaMais.repository.CursoRepository;

@Service
public class CursoService {

	final private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> buscarTodosCursos() {
        return cursoRepository.findAll();
    }

    public List<Curso> buscarCursosAtivos() {
        return cursoRepository.findByAtivoTrueOrderByAcessosDesc();
    }

    public List<Curso> buscarDestaques() {
        return cursoRepository.findTop8ByAtivoTrueOrderByAcessosDesc();
    }

    public Curso buscarCursoPorId(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.orElse(null);
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizarCurso(Long id, Curso cursoAtualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            BeanUtils.copyProperties(cursoAtualizado, curso, "id");
            return cursoRepository.save(curso);
        }
        return null;
    }

    public Boolean apagarCurso(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void incrementarAcessos(Long id) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setAcessos(curso.getAcessos() + 1);
            cursoRepository.save(curso);
        }
    }
    
    public List<Curso> buscarPorTitulo(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return List.of();
        }
        return cursoRepository.findByTituloContainingIgnoreCaseAndAtivoTrue(termo);
    }
}
