package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.MaquinaTipo;
import br.unitau.inf.manutencao.model.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

public class MaquinaPutDTO{
	@NotBlank
	@Length(max = 50)
	private String numSerie;
	
	@NotNull
	private Long maquinaTipoId;
	
	@NotBlank
	@Length(max = 50)
	private String modelo;
	
	@NotNull
	private long clienteId;
	
	//getters
	
		public String getNs() {
			return numSerie;
		}
		
		public Long getMaquinaTipoId() {
			return maquinaTipoId;
		}
		
		public String getModelo() {
			return modelo;
		}
		
		public Long getClienteId() {
			return clienteId;
		}
		
	
	public void update(Maquina item, MaquinaTipo maquinaTipo, Cliente cliente) {
		if (this.numSerie != null) item.setNs(this.numSerie);
	    if (maquinaTipo != null) item.setMaquinaTipo(maquinaTipo);
	    if (this.modelo != null) item.setModelo(this.modelo);
	    if (cliente != null) item.setCliente(cliente);
	}
}