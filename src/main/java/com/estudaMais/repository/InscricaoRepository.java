package com.estudaMais.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudaMais.entity.Inscricao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByAlunoId(Long alunoId);

    List<Inscricao> findByAlunoIdAndAtivoTrue(Long alunoId);

    Optional<Inscricao> findByAlunoIdAndCursoId(Long alunoId, Long cursoId);

    boolean existsByAlunoIdAndCursoIdAndAtivoTrue(Long alunoId, Long cursoId);
    
    @Query("SELECT COUNT(DISTINCT i.aluno.id) FROM Inscricao i " +
    	       "WHERE i.curso.professor.id = :professorId AND i.ativo = true")
    	int contarAlunosAtivosPorProfessor(@Param("professorId") Long professorId);
}
