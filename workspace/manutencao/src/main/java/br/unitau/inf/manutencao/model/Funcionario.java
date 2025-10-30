package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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
	private LocalDate ingresso;

	@Column(nullable = false)
	private LocalDate demissao;	

	@ManyToOne
	@JoinColumn(name = "turno_id", nullable = false)
	private Turno turno;
	
	
	
	//getters
	
		public Long getId() {
			return id;
		}
		
		public String getNome() {
			return nome;
		}
		
		public String getCelular() {
			return celular;
		}
		
		public String getCpf() {
			return cpf;
		}
		
		public String getEmailPessoal() {
			return emailPessoal;
		}
		
		public String getEmailCorp() {
			return emailCorp;
		}
		
		public LocalDate getIngresso() {
			return ingresso;
		}
		
		public LocalDate getDemissao() {
			return demissao;
		}
		
		public Turno getTurno(){
			return turno;
		}
		
		
		
		//setters
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public void setCelular(String num) {
			this.celular = num;
		}
		
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		
		public void setEmailPessoal(String email) {
			this.emailPessoal = email;
		}
		
		public void setEmailCorp(String email) {
			this.emailCorp = email;
		}
		
		public void setIngresso(LocalDate date) {
			this.ingresso = date;
		}
		
		public void setDemissao(LocalDate date) {
			this.demissao = date;
		}
		
		public void setTurno(Turno turno) {
			this.turno = turno;
		}
}