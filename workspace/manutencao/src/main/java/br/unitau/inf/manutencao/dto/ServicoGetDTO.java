package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.unitau.inf.manutencao.model.Servico;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Maquina;

import br.unitau.inf.manutencao.dto.ComponentesGetDTO;
import br.unitau.inf.manutencao.dto.MaquinaGetDTO;


public class ServicoGetDTO{
	private Long id;
	private MaquinaGetDTO maquina;
	private String descComponenteOrig;
	private ComponentesGetDTO componenteTroca;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime servicoData;	
	
	public ServicoGetDTO() {
		
	}
	
	 public ServicoGetDTO(Servico tipo) {
	        this.id = tipo.getId();
	        this.maquina = new MaquinaGetDTO(tipo.getMaquina());
	        this.descComponenteOrig = tipo.getDescComponenteOrig();
	        this.componenteTroca = new ComponentesGetDTO(tipo.getComponenteTroca());
	        this.servicoData = tipo.getServicoData();
	        
	       }
	
	public Long getId() {
		return id;
	}
	
	public MaquinaGetDTO getMaquina() {
		return maquina;
	}
	
	public String getDescComponenteOrig() {
		return descComponenteOrig;
	}
	
	public ComponentesGetDTO getComponenteTroca() {
		return componenteTroca;
	}
	
	public LocalDateTime getServicoData() {
		return servicoData;
	}
	
	
	public static List<ServicoGetDTO> convert(List<Servico> lista){
		return lista.stream().map(ServicoGetDTO::new).collect(Collectors.toList());
	}
}
