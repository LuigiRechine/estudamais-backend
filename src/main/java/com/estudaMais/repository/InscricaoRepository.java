package com.estudaMais.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudaMais.entity.Inscricao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByAlunoId(Long alunoId);

    List<Inscricao> findByAlunoIdAndAtivoTrue(Long alunoId);

    Optional<Inscricao> findByAlunoIdAndCursoId(Long alunoId, Long cursoId);

    boolean existsByAlunoIdAndCursoIdAndAtivoTrue(Long alunoId, Long cursoId);
}
