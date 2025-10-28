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
	
	
	
	
	
	//getters n setters
}