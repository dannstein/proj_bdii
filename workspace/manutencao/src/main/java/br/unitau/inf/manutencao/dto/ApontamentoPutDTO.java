package br.unitau.inf.manutencao.dto;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Apontamento;

import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;


public class ApontamentoPutDTO{
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
	
	
	public void update(Apontamento item, Servico servicoItem, Funcionario funcionarioItem) {
		if (servicoItem != null) item.setServico(servicoItem);
	    if (funcionarioItem != null) item.setFuncionario(funcionarioItem);
	    if (this.horas != null) item.setHoras(this.horas);
	}
}