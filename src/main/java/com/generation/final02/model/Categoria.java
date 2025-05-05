package com.generation.final02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //transformando a classe em table (JPA) create table
@Table(name = "tb_postagem")  // nome da tabela
public class Categoria {
	
	
	@Id // coluna primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
	private Long id; 

	@NotBlank
	@Size(min = 05, max = 50)
	private String categorias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	
	
}
