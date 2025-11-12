package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "componentes")
public class Componentes{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "componentetipo_id", nullable = false)
	private ComponenteTipo tipo;
	
	@Column(nullable = false)
	private Integer qtd;
	
	@Column(name = "precounit")
	private BigDecimal precoUnitario;
	
	
	
	
	
	//getters
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ComponenteTipo getComponenteTipo() {
		return tipo;
	}
	
	public Integer getQtd() {
		return qtd;
	}
	
	public BigDecimal getPrecoUnit() {
		return precoUnitario;
	}
	
	
	
	
	//setters
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setComponenteTipo(ComponenteTipo tipo) {
		this.tipo = tipo;
	}
	
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	public void setPrecoUnit(BigDecimal preco) {
		this.precoUnitario = preco;
	}
	
}