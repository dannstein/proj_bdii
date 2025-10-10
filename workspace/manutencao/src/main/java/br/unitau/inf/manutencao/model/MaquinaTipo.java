package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maquinatipo")
public class MaquinaTipo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	
	
	
	
	//getters n setters
}