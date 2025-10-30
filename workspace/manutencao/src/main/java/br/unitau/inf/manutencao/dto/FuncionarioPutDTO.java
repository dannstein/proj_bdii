package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Turno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

public class FuncionarioPutDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	@NotBlank
	@Length(max = 11)
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Email
	private String emailPessoal;
	
	@Email
	private String emailCorp;
	
	@Pattern(regexp = "\\d{11}", message = "O celular deve ter 11 dígitos")
	private String celular;
	
	@NotBlank
	private LocalDate ingresso;
	
	private LocalDate demissao;
	
	private Long turnoId;
	
	//getters
	
		public String getNome() {
			return nome;
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
		
		public String getCelular() {
			return celular;
		}
		
		public Long getTurnoId() {
			return turnoId;
		}
	
	public void update(Funcionario item, Turno turno) {
		if (this.nome != null) item.setNome(this.nome);
	    if (this.cpf != null) item.setCpf(this.cpf);
	    if (this.emailPessoal != null) item.setEmailPessoal(this.emailPessoal);
	    if (this.emailCorp != null) item.setEmailCorp(this.emailCorp);
	    if (this.ingresso != null) item.setIngresso(this.ingresso);
	    if (this.demissao != null) item.setDemissao(this.demissao);
	    if (this.celular != null) item.setCelular(this.celular);
	    if (turno != null) item.setTurno(turno);
	}
}