package com.estudaMais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudaMais.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	Professor findByEmail(String email);
}
