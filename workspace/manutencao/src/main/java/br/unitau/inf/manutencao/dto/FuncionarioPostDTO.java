package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Turno;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioPostDTO{
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
	
	@NotNull
	@PastOrPresent
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "America/Sao_Paulo")	
	private LocalDate ingresso;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "America/Sao_Paulo")	
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
	
	//setters
	
	public void setNome(String nome) { 
		this.nome = nome;
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
	
	public void setCelular(String tel) {
		this.celular = tel;
	}
	
	public void setTurnoId(Long id) {
		this.turnoId = id;
	}
	
	public Funcionario convert(Turno turno) {
	    Funcionario ret = new Funcionario();
	    ret.setNome(this.nome);
	    ret.setCpf(this.cpf);
	    ret.setEmailPessoal(this.emailPessoal);
	    ret.setEmailCorp(this.emailCorp);
	    ret.setIngresso(this.ingresso);
	    ret.setDemissao(this.demissao);
	    ret.setCelular(this.celular);
	    ret.setTurno(turno);
	    return ret;
	}
}