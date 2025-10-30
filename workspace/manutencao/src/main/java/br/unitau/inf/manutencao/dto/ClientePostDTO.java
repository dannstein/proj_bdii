package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClientePostDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	@NotBlank
	@Length(max = 11)
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Email
	private String email;
	
	@Pattern(regexp = "\\d{11}", message = "O celular deve ter 11 dígitos")
	private String celular;
	
	//getters
	
	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCelular() {
		return celular;
	}
	
	//setters
	
	public void setNome(String nome) { 
		this.nome = nome;
	}

	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCelular(String tel) {
		this.celular = tel;
	}
	
	public Cliente convert() {
	    Cliente ret = new Cliente();
	    ret.setNome(this.nome);
	    ret.setCpf(this.cpf);
	    ret.setEmail(this.email);
	    ret.setCelular(this.celular);
	    return ret;
	}
}