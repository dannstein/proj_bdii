package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "componentetipo")
public class ComponenteTipo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	
	
	
	
	//getters
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	//setters
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}