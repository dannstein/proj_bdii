package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.OsStatus;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class OsStatusPutDTO{
	@NotBlank
	@Length(max = 50)
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	
	public void update(OsStatus item) {
		item.setTipo(this.tipo);
	}
}