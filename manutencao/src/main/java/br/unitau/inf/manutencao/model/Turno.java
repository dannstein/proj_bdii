package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class Turno{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	
	private LocalDateTime entrada;
	
	private LocalDateTime saida;
	
	
	
	//getters n setters
}