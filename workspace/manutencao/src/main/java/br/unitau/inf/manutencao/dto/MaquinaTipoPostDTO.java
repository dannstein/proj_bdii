package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.MaquinaTipo;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class MaquinaTipoPostDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	 public void setNome(String nome) { 
	        this.nome = nome;
	    }
	
	public MaquinaTipo convert() {
		MaquinaTipo ret = new MaquinaTipo();
		ret.setNome(this.nome);
		
		return ret;
	}
}