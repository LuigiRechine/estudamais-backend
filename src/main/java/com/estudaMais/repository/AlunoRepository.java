package com.estudaMais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudaMais.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Aluno findByEmail(String email);
}
