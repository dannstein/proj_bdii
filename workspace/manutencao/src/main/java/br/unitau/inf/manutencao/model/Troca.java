package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import br.unitau.inf.manutencao.model.Componentes;

@Entity
@Table(name = "trovas")
public class Troca{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "servicos_id", nullable = false)
	private Servico servico;
	
	@Column(name = "compriginal")
	private String compOriginal;
	
	@ManyToOne
	@JoinColumn(name = "componentes_id", nullable = false)
	private Componentes componente;
	
	
	
	
	//getters n setters
}