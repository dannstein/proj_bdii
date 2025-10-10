package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apontamento")
public class Apontamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "servicos_id", nullable = false)
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable=false)
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private BigDecimal horas;
	
	//getters n setters
}