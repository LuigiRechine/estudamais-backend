package com.estudaMais.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Aula")
@Data @NoArgsConstructor @AllArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private String descricao;

    @NotBlank
    private String videoUrl;     

    private Integer duracaoMinutos;

    private Integer ordem;       

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties("aulas")
    private Curso curso;

    private Boolean ativo = true;
}
