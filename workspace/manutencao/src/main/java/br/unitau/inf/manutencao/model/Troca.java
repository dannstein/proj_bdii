package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.model.Servico;

@Entity
@Table(name = "trocas")
public class Troca{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "servicos_id", nullable = false)
	private Servico servico;
	
	@Column(name = "comporiginal")
	private String compOriginal;
	
	@ManyToOne
	@JoinColumn(name = "componentes_id", nullable = false)
	private Componentes componente;
	
	
	
	
	//getters
	
			public Long getId() {
				return id;
			}
			
			public Servico getServico() {
				return servico;
			}
			
			public String getCompOriginal() {
				return compOriginal;
			}
			
			public Componentes getComponenteTroca() {
				return componente;
			}
				
			
			//setters
			
			public void setId(Long id) {
				this.id = id;
			}
			
			public void setServico(Servico serv) {
				this.servico = serv;
			}
			
			public void setCompOriginal(String desc) {
				this.compOriginal = desc;
			}
			
			public void setComponenteTroca(Componentes comp) {
				this.componente = comp;
			}
			
}