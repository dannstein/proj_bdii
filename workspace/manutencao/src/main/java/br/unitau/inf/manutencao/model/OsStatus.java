package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "osstatus")
public class OsStatus{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String tipo;
	
	
	
	
	
	//getters n setters
}