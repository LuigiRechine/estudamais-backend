package com.estudaMais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.estudaMais.entity.Professor;
import com.estudaMais.repository.ProfessorRepository;

@Service
public class ProfessorService {

final private ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public List<Professor> buscarTodosProfessors(){
		return professorRepository.findAll();
	}
	
	public Professor buscarProfessorPorId(Long id) {
		Optional <Professor> professor = professorRepository.findById(id);
		return professor.orElse(null);
	}
	
	public Professor salvarProfessor(Professor atProfessor) {
		return professorRepository.save(atProfessor);
	}
	
	public Professor atualizarProfessor(Long id, Professor atProfessor) {
		Optional <Professor> exeProfessor = professorRepository.findById(id);
		if(exeProfessor.isPresent()) {
			Professor professor = exeProfessor.get();
			BeanUtils.copyProperties(atProfessor, professor, "id");
			return professorRepository.save(professor);
		}
		return null;
	}
	
	public Boolean apagarProfessor(Long id) {
		Optional<Professor> exeProfessor = professorRepository.findById(id);
		if(exeProfessor.isPresent()) {
			professorRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Professor authenticate(String email, String password) {
		Professor mail = professorRepository.findByEmail(email);
		
		if(mail != null && mail.getPassword().equals(password)) {
			return mail;
		}
		return null;
		
	}

}
