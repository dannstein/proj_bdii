package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitau.inf.manutencao.model.MaquinaTipo;

public class MaquinaTipoGetDTO{
	private Long id;
	private String nome;
	
	public MaquinaTipoGetDTO() {
		
	}
	
	 public MaquinaTipoGetDTO(MaquinaTipo tipo) {
	        this.id = tipo.getId();
	        this.nome = tipo.getNome();
	    }
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static List<MaquinaTipoGetDTO> convert(List<MaquinaTipo> lista){
		return lista.stream().map(MaquinaTipoGetDTO::new).collect(Collectors.toList());
	}
}
