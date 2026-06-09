package com.estudaMais.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudaMais.entity.Aluno;
import com.estudaMais.entity.Curso;
import com.estudaMais.entity.Inscricao;
import com.estudaMais.repository.AlunoRepository;
import com.estudaMais.repository.CursoRepository;
import com.estudaMais.repository.InscricaoRepository;

import jakarta.transaction.Transactional;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository,
                            AlunoRepository alunoRepository,
                            CursoRepository cursoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Inscricao matricularAluno(Long alunoId, Long cursoId) {
        // Verifica se já está matriculado
        if (inscricaoRepository.existsByAlunoIdAndCursoIdAndAtivoTrue(alunoId, cursoId)) {
            throw new RuntimeException("Aluno já está matriculado neste curso");
        }

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Inscricao inscricao = new Inscricao();
        inscricao.setAluno(aluno);
        inscricao.setCurso(curso);

        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarMatriculasDoAluno(Long alunoId) {
        return inscricaoRepository.findByAlunoIdAndAtivoTrue(alunoId);
    }

    public Inscricao buscarPorId(Long id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
    }

    @Transactional
    public void cancelarMatricula(Long alunoId, Long cursoId) {
        Inscricao inscricao = inscricaoRepository.findByAlunoIdAndCursoId(alunoId, cursoId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        inscricao.setAtivo(false);
        inscricaoRepository.save(inscricao);
    }

    public void atualizarProgresso(Long inscricaoId, Integer percentual) {
        Inscricao inscricao = buscarPorId(inscricaoId);
        inscricao.setProgressoPercentual(percentual);
        inscricaoRepository.save(inscricao);
    }
}
