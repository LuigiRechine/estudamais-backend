package com.estudaMais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudaMais.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	List<Curso> findByAtivoTrueOrderByAcessosDesc();
	List<Curso> findTop8ByAtivoTrueOrderByAcessosDesc();
	List<Curso> findByTituloContainingIgnoreCaseAndAtivoTrue(String titulo);
}
