package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitau.inf.manutencao.model.Cliente;

public class ClienteGetDTO{
	private Long id;
	private String nome;
	private String celular;
	private String cpf;
	private String email;
	
	public ClienteGetDTO() {
		
	}
	
	 public ClienteGetDTO(Cliente tipo) {
	        this.id = tipo.getId();
	        this.nome = tipo.getNome();
	        this.celular = tipo.getCelular();
	        this.cpf = tipo.getCpf();
	        this.email = tipo.getEmail();
	 }
	
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
	
	
	public static List<ClienteGetDTO> convert(List<Cliente> lista){
		return lista.stream().map(ClienteGetDTO::new).collect(Collectors.toList());
	}
}
