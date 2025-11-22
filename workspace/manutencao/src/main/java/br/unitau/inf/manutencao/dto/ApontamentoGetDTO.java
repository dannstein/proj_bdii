package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.unitau.inf.manutencao.model.Apontamento;

import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Funcionario;

import br.unitau.inf.manutencao.dto.ComponentesGetDTO;
import br.unitau.inf.manutencao.dto.FuncionarioGetDTO;


public class ApontamentoGetDTO{
	private Long id;
	private ServicoGetDTO servico;
	private BigDecimal horas;
	private FuncionarioGetDTO funcionario;
	
	
	public ApontamentoGetDTO() {
		
	}
	
	 public ApontamentoGetDTO(Apontamento tipo) {
	        this.id = tipo.getId();
	        this.servico = new ServicoGetDTO(tipo.getServico());
	        this.funcionario = new FuncionarioGetDTO(tipo.getFuncionario());
	        this.horas = tipo.getHoras();
	        
	       }
	
	public Long getId() {
		return id;
	}
	
	public ServicoGetDTO getServico() {
		return servico;
	}
	
	public FuncionarioGetDTO getFuncionario() {
		return funcionario;
	}
	
	public BigDecimal getHoras() {
		return horas;
	}
	
	
	
	public static List<ApontamentoGetDTO> convert(List<Apontamento> lista){
		return lista.stream().map(ApontamentoGetDTO::new).collect(Collectors.toList());
	}
}
