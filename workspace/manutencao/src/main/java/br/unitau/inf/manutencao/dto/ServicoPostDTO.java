package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.formatter.LocalDateTimeFormatter;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Maquina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.validator.constraints.Length;


public class ServicoPostDTO{
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
	
	//setters
	
	public void setMaquinaId(Long id) { 
		this.maquinaId = id;
	}

	
	public void setDescComponenteOrig(String nome) {
		this.descComponenteOrig = nome;
	}
	
	public void setComponenteTrocaId(Long id) {
		this.componenteTrocaId = id;
	}
	
	public void setServicoData(LocalDateTime data) {
		this.servicoData = data;
	}
	
	
	public Servico convert(Maquina maquina, Componentes componente) {
	    Servico ret = new Servico();
	    ret.setMaquina(maquina);
	    ret.setDescComponenteOrig(this.descComponenteOrig);
	    ret.setComponenteTroca(componente);
	    ret.setServicoData(this.servicoData);
	    return ret;
	}
}