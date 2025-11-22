package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Troca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.validator.constraints.Length;


public class TrocasPutDTO{
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
	
	
	public void update(Troca item, Servico servicoItem, Componentes componenteItem) {
		if (servicoItem != null) item.setServico(servicoItem);
	    if (this.compOriginal != null) item.setCompOriginal(this.compOriginal);
	    if (componenteItem != null) item.setComponenteTroca(componenteItem);
	}
}