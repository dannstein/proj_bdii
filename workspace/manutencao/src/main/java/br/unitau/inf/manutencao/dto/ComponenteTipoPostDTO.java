package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.ComponenteTipo;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ComponenteTipoPostDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	 public void setNome(String nome) { 
	        this.nome = nome;
	    }
	
	public ComponenteTipo convert() {
		ComponenteTipo ret = new ComponenteTipo();
		ret.setNome(this.nome);
		
		return ret;
	}
}