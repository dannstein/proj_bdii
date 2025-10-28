package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;



@Entity
@Table(name = "cliente")
public class Cliente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String celular;
	
	private String cpf;
	
	private String email;
	
	
	//getters n setters
}