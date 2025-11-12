package br.unitau.inf.manutencao.dto;


import br.unitau.inf.manutencao.formatter.LocalDateTimeFormatter;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.Servico;


import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.validator.constraints.Length;


public class ServicoPatchDTO{
	private Long maquinaId;
	
	@Length(max = 50)
	private String descComponenteOrig;
	
	private Long componenteTrocaId;
	    
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