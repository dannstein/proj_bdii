package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "componentes")
public class Componentes{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "componentetipo_id", nullable = false)
	private ComponenteTipo tipo;
	
	@Column(nullable = false)
	private Integer qtd;
	
	@Column(name = "precounit")
	private BigDecimal precoUnitario;
	
	
	
	
	
	//getters n setters
}