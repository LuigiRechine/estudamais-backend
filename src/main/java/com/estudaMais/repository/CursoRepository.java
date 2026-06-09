package com.estudaMais.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudaMais.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByAtivoTrueOrderByAcessosDesc();
    List<Curso> findTop8ByAtivoTrueOrderByAcessosDesc();
    List<Curso> findByTituloContainingIgnoreCaseAndAtivoTrue(String titulo);
    
    List<Curso> findByProfessorIdAndAtivoTrue(Long professorId);
    
    Optional<Curso> findByIdAndProfessorId(Long id, Long professorId);
}
