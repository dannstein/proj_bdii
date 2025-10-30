package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.ComponenteTipo;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ComponenteTipoPutDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void update(ComponenteTipo item) {
		item.setNome(this.nome);
	}
}