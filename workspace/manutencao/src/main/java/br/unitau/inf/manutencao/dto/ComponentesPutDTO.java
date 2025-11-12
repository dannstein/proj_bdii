package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.ComponenteTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;


public class ComponentesPutDTO{
	@NotBlank
	@Length(max = 50)
	private String nome;
	
	@NotNull
	private Long componenteTipoId;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private BigDecimal precoUnitario;
	
	
	//getters
	
	public String getNome() {
		return nome;
	}
	
	public Long getComponenteTipoId() {
		return componenteTipoId;
	}
	
	public Integer getQtd() {
		return quantidade;
	}
	
	public BigDecimal getPrecoUnit() {
		return precoUnitario;
	}
	
	
	
	public void update(Componentes item, ComponenteTipo componenteTipo) {
		if (this.nome != null) item.setNome(this.nome);
	    if (componenteTipo != null) item.setComponenteTipo(componenteTipo);
	    if (this.quantidade != null) item.setQtd(this.quantidade);
	    if (this.precoUnitario != null) item.setPrecoUnit(this.precoUnitario);
	}
}