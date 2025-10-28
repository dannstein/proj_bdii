package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.MaquinaTipo;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class MaquinaTipoPutDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void update(MaquinaTipo item) {
		item.setNome(this.nome);
	}
}