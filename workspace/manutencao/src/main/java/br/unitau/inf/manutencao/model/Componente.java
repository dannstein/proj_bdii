package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "componentes")
public class Componente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "componentetipo_id", nullable = false)
	private Integer componentetipo;
	
	@Column(nullable = false)
	private Integer qtd;
	
	@Column(nullable = false)
	private BigDecimal precounit;
	
	
	
	//getters n setters
}