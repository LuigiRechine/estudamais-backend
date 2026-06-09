package com.estudaMais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudaMais.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    
    List<Aula> findByCursoId(Long cursoId);
    
    List<Aula> findByCursoIdAndAtivoTrue(Long cursoId);
}
