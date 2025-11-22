package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Troca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.validator.constraints.Length;


public class TrocasPostDTO{
	@NotNull
	private Long servicoId;
	
	@NotBlank
	@Length(max = 50)
	private String compOriginal;
	
	@NotNull
	private Long componenteTrocaId;

	
	
	//getters
	
	public Long getServicoId() {
		return servicoId;
	}
	
	public String getCompOriginal() {
		return compOriginal;
	}
	
	public Long getComponenteTrocaId() {
		return componenteTrocaId;
	}
	
	//setters
	
	public void setServicoId(Long id) { 
		this.servicoId = id;
	}

	
	public void setCompOriginal(String nome) {
		this.compOriginal = nome;
	}
	
	public void setComponenteTrocaId(Long id) {
		this.componenteTrocaId = id;
	}
	
	public Troca convert(Servico servico, Componentes componente) {
	    Troca ret = new Troca();
	    ret.setServico(servico);
	    ret.setCompOriginal(this.compOriginal);
	    ret.setComponenteTroca(componente);
	    return ret;
	}
}