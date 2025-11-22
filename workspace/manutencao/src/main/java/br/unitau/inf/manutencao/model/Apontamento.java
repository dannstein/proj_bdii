package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apontamento")
public class Apontamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "servicos_id", nullable = false)
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable=false)
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private BigDecimal horas;
	
	//getters
	
	public Long getId() {
		return id;
	}
	
	public Servico getServico() {
		return servico;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public BigDecimal getHoras() {
		return horas;
	}
		
	
	//setters
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setServico(Servico serv) {
		this.servico = serv;
	}
	
	public void setFuncionario(Funcionario f) {
		this.funcionario = f;
	}
	
	public void setHoras(BigDecimal h) {
		this.horas = h;
	}
}