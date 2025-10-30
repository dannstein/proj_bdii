package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalTime;

import br.unitau.inf.manutencao.model.Turno;

public class TurnoGetDTO{
	private Long id;
	private String tipo;
	private LocalTime entrada;
	private LocalTime saida;

	public TurnoGetDTO() {
		
	}
	
	 public TurnoGetDTO(Turno tipo) {
	        this.id = tipo.getId();
	        this.tipo = tipo.getTipo();
	        this.entrada = tipo.getEntrada();
	        this.saida = tipo.getSaida();
	 }
	
	public Long getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public LocalTime getEntrada() {
		return entrada;
	}
	
	public LocalTime getSaoda() {
		return saida;
	}
	

	
	public static List<TurnoGetDTO> convert(List<Turno> lista){
		return lista.stream().map(TurnoGetDTO::new).collect(Collectors.toList());
	}
}
