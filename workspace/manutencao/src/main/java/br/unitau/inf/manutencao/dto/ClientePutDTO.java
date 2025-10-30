package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import org.hibernate.validator.constraints.Length;

public class ClientePutDTO{
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
	
	public void update(Cliente item) {
		 if (this.nome != null) item.setNome(this.nome);
	    if (this.cpf != null) item.setCpf(this.cpf);
	    if (this.email != null) item.setEmail(this.email);
	    if (this.celular != null) item.setCelular(this.celular);
	}
}