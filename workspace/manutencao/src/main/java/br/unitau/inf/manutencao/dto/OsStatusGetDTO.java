package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitau.inf.manutencao.model.OsStatus;

public class OsStatusGetDTO{
	private Long id;
	private String tipo;
	
	public OsStatusGetDTO() {
		
	}
	
	 public OsStatusGetDTO(OsStatus tipo) {
	        this.id = tipo.getId();
	        this.tipo = tipo.getTipo();
	    }
	
	public Long getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public static List<OsStatusGetDTO> convert(List<OsStatus> lista){
		return lista.stream().map(OsStatusGetDTO::new).collect(Collectors.toList());
	}
}
