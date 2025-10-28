package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maquinas")
public class Maquina{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer ns;
	
	@ManyToOne
	@JoinColumn(name = "maquinatipo_id", nullable = false)
	private MaquinaTipo Tipo;
	
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	//getters n setters
}