package com.estudaMais.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Curso")
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    private String titulo;

    @NotBlank
    private String categoria;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer duracaoHoras;
    
    @NotBlank
    private String imagem;
    
    private String link;
    
    private Integer acessos = 0;
    
    private Boolean ativo = true;
    
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
