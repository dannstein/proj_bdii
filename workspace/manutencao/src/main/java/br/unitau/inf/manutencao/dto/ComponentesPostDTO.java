package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.ComponenteTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;


public class ComponentesPostDTO{
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
	
	//setters
	
	public void setNome(String nome) { 
		this.nome = nome;
	}

	
	public void setComponenteTipoId(Long id) {
		this.componenteTipoId = id;
	}
	
	public void setQtd(Integer qtd) {
		this.quantidade = qtd;
	}
	
	public void setPrecoUnit(BigDecimal preco) {
		this.precoUnitario = preco;
	}
	
	
	public Componentes convert(ComponenteTipo componenteTipo) {
	    Componentes ret = new Componentes();
	    ret.setNome(this.nome);
	    ret.setComponenteTipo(componenteTipo);
	    ret.setQtd(this.quantidade);
	    ret.setPrecoUnit(this.precoUnitario);
	    return ret;
	}
}