package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import br.unitau.inf.manutencao.model.Maquina;

import br.unitau.inf.manutencao.model.MaquinaTipo;
import br.unitau.inf.manutencao.model.Cliente;

import br.unitau.inf.manutencao.dto.MaquinaTipoGetDTO;
import br.unitau.inf.manutencao.dto.ClienteGetDTO;


public class MaquinaGetDTO{
	private Long id;
	private String numeroSerie;
	private MaquinaTipoGetDTO maquinaTipo;
	private String modelo;
	private ClienteGetDTO cliente;
	
	
	
	
	public MaquinaGetDTO() {
		
	}
	
	 public MaquinaGetDTO(Maquina tipo) {
	        this.id = tipo.getId();
	        this.numeroSerie = tipo.getNs();
	        this.maquinaTipo = new MaquinaTipoGetDTO(tipo.getMaquinaTipo());
	        this.modelo = tipo.getModelo();
	        this.cliente = new ClienteGetDTO(tipo.getCliente());
	        
	       }
	
	public Long getId() {
		return id;
	}
	
	public String getNs() {
		return numeroSerie;
	}
	
	public MaquinaTipoGetDTO getMaquinaTipo() {
		return maquinaTipo;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public ClienteGetDTO getCliente() {
		return cliente;
	}
	
	
	public static List<MaquinaGetDTO> convert(List<Maquina> lista){
		return lista.stream().map(MaquinaGetDTO::new).collect(Collectors.toList());
	}
}
