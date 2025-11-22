package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Apontamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;


public class ApontamentoPostDTO{
	@NotNull
	private Long servicoId;
	
	@NotNull
	private BigDecimal horas;
	
	@NotNull
	private Long funcionarioId;

	
	
	//getters
	
	public Long getServicoId() {
		return servicoId;
	}
	
	public BigDecimal getHoras() {
		return horas;
	}
	
	public Long getFuncionarioId() {
		return funcionarioId;
	}
	
	//setters
	
	public void setServicoId(Long id) { 
		this.servicoId = id;
	}

	
	public void setHoras(BigDecimal h) {
		this.horas = h;
	}
	
	public void setFuncionarioId(Long id) {
		this.funcionarioId = id;
	}
	
	public Apontamento convert(Servico servico, Funcionario funcionario) {
	    Apontamento ret = new Apontamento();
	    ret.setServico(servico);
	    ret.setFuncionario(funcionario);
	    ret.setHoras(this.horas);
	    return ret;
	}
}