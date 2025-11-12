package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import br.unitau.inf.manutencao.model.Componentes;

import br.unitau.inf.manutencao.model.ComponenteTipo;

import br.unitau.inf.manutencao.dto.ComponenteTipoGetDTO;


public class ComponentesGetDTO{
	private Long id;
	private String nome;
	private ComponenteTipoGetDTO componenteTipo;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	
	
	
	public ComponentesGetDTO() {
		
	}
	
	 public ComponentesGetDTO(Componentes tipo) {
	        this.id = tipo.getId();
	        this.nome = tipo.getNome();
	        this.componenteTipo = new ComponenteTipoGetDTO(tipo.getComponenteTipo());
	        this.quantidade = tipo.getQtd();
	        this.precoUnitario = tipo.getPrecoUnit();
	        
	       }
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ComponenteTipoGetDTO getComponenteTipo() {
		return componenteTipo;
	}
	
	public Integer getQtd() {
		return quantidade;
	}
	
	public BigDecimal getPrecoUnit() {
		return precoUnitario;
	}
	
	
	public static List<ComponentesGetDTO> convert(List<Componentes> lista){
		return lista.stream().map(ComponentesGetDTO::new).collect(Collectors.toList());
	}
}
