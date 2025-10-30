package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "turno")
public class Turno{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	
	private LocalTime entrada;
	
	private LocalTime saida;
	
	
	
	//getters
	
		public Long getId() {
			return id;
		}
		
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
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		public void setEntrada(LocalTime time) {
			this.entrada = time;
		}
		
		public void setSaida(LocalTime time) {
			this.saida = time;
		}
		
}