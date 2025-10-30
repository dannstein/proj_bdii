package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Turno;
import br.unitau.inf.manutencao.formatter.LocalTimeFormatter;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

public class TurnoPostDTO {
    @NotNull
    private String tipo;
    @NotNull
    @JsonDeserialize(using = LocalTimeFormatter.class)
    private LocalTime entrada;
    @NotNull
    @JsonDeserialize(using = LocalTimeFormatter.class)
    private LocalTime saida;

    // getters
    public String getTipo() { 
    	return tipo; 
    }

    public LocalTime getEntrada() { 
    	return entrada; 
    }

    public LocalTime getSaida() { 
    	return saida; 
    }
    
    //setters
    
    public void setTipo(String tipo) { 
    	this.tipo = tipo; 
    }
    
    public void setEntrada(LocalTime entrada) { 
    	this.entrada = entrada; 
    }
    
    public void setSaida(LocalTime saida) { 
    	this.saida = saida; 
    }

    public Turno convert() {
        Turno t = new Turno();
        t.setTipo(this.tipo);
        t.setEntrada(this.entrada);
        t.setSaida(this.saida);
        return t;
    }
}
