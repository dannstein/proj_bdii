package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.OsStatus;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class OsStatusPostDTO{
	@NotBlank
	@Length(max = 50)
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	
	 public void setTipo(String tipo) { 
	        this.tipo = tipo;
	    }
	
	public OsStatus convert() {
		OsStatus ret = new OsStatus();
		ret.setTipo(this.tipo);
		
		return ret;
	}
}