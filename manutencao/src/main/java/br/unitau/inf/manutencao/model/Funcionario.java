package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcionario")
public class Funcionario{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String celular;
	
	private String cpf;
	
	@Column(name = "emailpessoal")
	private String emailPessoal;
	
	@Column(name = "emailcorp")
	private String emailCorp;
	
	@Column(nullable = false)
	private LocalDateTime ingresso;

	@Column(nullable = false)
	private LocalDateTime regresso;	

	@ManyToOne
	@JoinColumn(name = "turno_id", nullable = false)
	private Turno turno;
	
	
	
	
	//getters n setters
}