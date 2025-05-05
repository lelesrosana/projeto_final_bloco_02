package com.generation.final02.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity //transformando a classe em table (JPA) create table
@Table(name = "tb_produto")  // nome da tabela
public class Produto {
	
	
	@Id // coluna primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
	private Long id; 
	
	@NotBlank  // compativel com String
	@Size(min = 05, max = 100) // define o tamanho
	private String nome;
	
	
	@Size(min = 10, max = 200)
	private String descricao;
	
	@NotNull
	@UpdateTimestamp
	private LocalDateTime validade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getValidade() {
		return validade;
	}

	public void setValidade(LocalDateTime validade) {
		this.validade = validade;
	}
	
	
}
