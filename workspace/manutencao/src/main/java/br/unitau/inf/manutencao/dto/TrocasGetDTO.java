package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.unitau.inf.manutencao.model.Troca;

import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Componentes;

import br.unitau.inf.manutencao.dto.ComponentesGetDTO;
import br.unitau.inf.manutencao.dto.ServicoGetDTO;


public class TrocasGetDTO{
	private Long id;
	private ServicoGetDTO servico;
	private String compOriginal;
	private ComponentesGetDTO componenteTroca;
	
	
	public TrocasGetDTO() {
		
	}
	
	 public TrocasGetDTO(Troca tipo) {
	        this.id = tipo.getId();
	        this.servico = new ServicoGetDTO(tipo.getServico());
	        this.compOriginal = tipo.getCompOriginal();
	        this.componenteTroca = new ComponentesGetDTO(tipo.getComponenteTroca());
	        
	       }
	
	public Long getId() {
		return id;
	}
	
	public ServicoGetDTO getServico() {
		return servico;
	}
	
	public String getCompOriginal() {
		return compOriginal;
	}
	
	public ComponentesGetDTO getComponenteTroca() {
		return componenteTroca;
	}
	
	
	
	public static List<TrocasGetDTO> convert(List<Troca> lista){
		return lista.stream().map(TrocasGetDTO::new).collect(Collectors.toList());
	}
}
