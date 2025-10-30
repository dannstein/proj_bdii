package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;



@Entity
@Table(name = "cliente")
public class Cliente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String celular;
	
	private String cpf;
	
	private String email;
	
	
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
	
	public String getEmail() {
		return email;
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
	
	public void setEmail(String email) {
		this.email = email;
	}
}