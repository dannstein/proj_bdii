package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "osstatus")
public class OsStatus{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String tipo;
	
	
	
	
	
	//getters
	
		public Long getId() {
			return id;
		}
		
		public String getTipo() {
			return tipo;
		}
		
		//setters
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
}