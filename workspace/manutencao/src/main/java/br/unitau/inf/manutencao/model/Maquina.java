package br.unitau.inf.manutencao.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "maquinas")
public class Maquina{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String numserie;
	
	@ManyToOne
	@JoinColumn(name = "maquinatipo_id", nullable = false)
	private MaquinaTipo tipo;
	
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	//getters
	
			public Long getId() {
				return id;
			}
			
			public String getNs() {
				return numserie;
			}
			
			public MaquinaTipo getMaquinaTipo() {
				return tipo;
			}
			
			public String getModelo() {
				return modelo;
			}
			
			public Cliente getCliente() {
				return cliente;
			}
			
			
			
			
			//setters
			
			public void setId(Long id) {
				this.id = id;
			}
			
			public void setNs(String ns) {
				this.numserie = ns;
			}
			
			public void setMaquinaTipo(MaquinaTipo tipo) {
				this.tipo = tipo;
			}
			
			public void setModelo(String modelo) {
				this.modelo = modelo;
			}
			
			public void setCliente(Cliente cliente) {
				this.cliente = cliente;
			}
			
			
}