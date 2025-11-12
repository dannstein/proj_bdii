package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.MaquinaTipo;
import br.unitau.inf.manutencao.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class MaquinaPostDTO{
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
	
	//setters
	
	public void setNs(String ns) { 
		this.numSerie = ns;
	}

	
	public void setMaquinaTipoId(Long id) {
		this.maquinaTipoId = id;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setClienteId(Long id) {
		this.clienteId = id;
	}
	
	
	public Maquina convert(MaquinaTipo maquinaTipo, Cliente cliente) {
	    Maquina ret = new Maquina();
	    ret.setNs(this.numSerie);
	    ret.setMaquinaTipo(maquinaTipo);
	    ret.setModelo(this.modelo);
	    ret.setCliente(cliente);
	    return ret;
	}
}