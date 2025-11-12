package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Turno;


import java.time.LocalTime;


public class TurnoPatchDTO{
	private String tipo;
    private LocalTime entrada;
    private LocalTime saida;
	
	//getters
	
	public String getTipo() {
		return tipo;
	}
	
	public LocalTime getEntrada() {
		return entrada;
	}
	
	public LocalTime getSaida() {
		return saida;
	}
	
	
	public void update(Turno item) {
		if (this.tipo != null) item.setTipo(this.tipo);
	    if (this.entrada != null) item.setEntrada(this.entrada);
	    if (this.saida != null) item.setSaida(this.saida);
	}
}