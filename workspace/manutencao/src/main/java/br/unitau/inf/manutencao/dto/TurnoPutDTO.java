package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;


public class TurnoPutDTO{
	@NotBlank
	private String tipo;
	@NotNull
    private LocalTime entrada;
    @NotNull
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