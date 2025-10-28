package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import br.unitau.inf.manutencao.model.Componentes;

@Entity
@Table(name = "servicos")
public class Servico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_maq", nullable = false)
	private Maquina maquina; 
	
	@Column(name = "desc_comp_orig", nullable = false)
	private String descCompOrig;
	
	@ManyToOne
	@JoinColumn(name = "id_comp_troca", nullable = false)
	private Componentes componenteTroca;
	
	@Column(name = "servico_data")
	private LocalDateTime servicoData;
	
	
	
	
	
	//getters n setters
}