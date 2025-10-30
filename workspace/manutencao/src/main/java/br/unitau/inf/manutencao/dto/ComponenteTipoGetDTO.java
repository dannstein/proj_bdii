package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitau.inf.manutencao.model.ComponenteTipo;

public class ComponenteTipoGetDTO{
	private Long id;
	private String nome;
	
	public ComponenteTipoGetDTO() {
		
	}
	
	 public ComponenteTipoGetDTO(ComponenteTipo tipo) {
	        this.id = tipo.getId();
	        this.nome = tipo.getNome();
	    }
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static List<ComponenteTipoGetDTO> convert(List<ComponenteTipo> lista){
		return lista.stream().map(ComponenteTipoGetDTO::new).collect(Collectors.toList());
	}
}
