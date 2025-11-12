package br.unitau.inf.manutencao.dto;


import br.unitau.inf.manutencao.formatter.LocalDateTimeFormatter;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.Servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.validator.constraints.Length;


public class ServicoPutDTO{
	@NotNull
	private Long maquinaId;
	
	@NotBlank
	@Length(max = 50)
	private String descComponenteOrig;
	
	@NotNull
	private Long componenteTrocaId;
	
	@NotNull      
	private LocalDateTime servicoData;	
	
	
	//getters
	
	public Long getMaquinaId() {
		return maquinaId;
	}
	
	public String getDescComponenteOrig() {
		return descComponenteOrig;
	}
	
	public Long getComponenteTrocaId() {
		return componenteTrocaId;
	}
	
	public LocalDateTime getServicoData() {
		return servicoData;
	}
	
	
	
	public void update(Servico item, Maquina maquinaItem, Componentes componenteItem) {
		if (maquinaItem != null) item.setMaquina(maquinaItem);
	    if (this.descComponenteOrig != null) item.setDescComponenteOrig(this.descComponenteOrig);
	    if (componenteItem != null) item.setComponenteTroca(componenteItem);
	    if (this.servicoData != null) item.setServicoData(this.servicoData);
	}
}