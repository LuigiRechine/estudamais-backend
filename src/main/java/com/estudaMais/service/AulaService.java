package com.estudaMais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.estudaMais.entity.Aula;
import com.estudaMais.entity.Curso;
import com.estudaMais.repository.AulaRepository;
import com.estudaMais.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;

    public AulaService(AulaRepository aulaRepository, CursoRepository cursoRepository) {
        this.aulaRepository = aulaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Aula criarAula(Long cursoId, Aula aula) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aula.setCurso(curso);
        return aulaRepository.save(aula);
    }

    public List<Aula> listarPorCurso(Long cursoId) {
        return aulaRepository.findByCursoIdAndAtivoTrue(cursoId);
    }

    public Aula buscarPorId(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    public Aula atualizarAula(Long id, Aula aulaAtualizado) {
        Optional<Aula> aulaExistente = aulaRepository.findById(id);
        if (aulaExistente.isPresent()) {
            Aula aula = aulaExistente.get();
            BeanUtils.copyProperties(aulaAtualizado, aula, "id");
            return aulaRepository.save(aula);
        }
        return null;
    }

    public Boolean apagarAula(Long id) {
        Optional<Aula> aula = aulaRepository.findById(id);
        if (aula.isPresent()) {
            aulaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
