package br.unitau.inf.manutencao.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.unitau.inf.manutencao.model.Componentes;

@Entity
@Table(name = "servicos")
public class Servico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_maq", nullable = false)
	private Maquina maquina; 
	
	@Column(name = "desc_comp_orig", nullable = false)
	private String descCompOrig;
	
	@ManyToOne
	@JoinColumn(name = "id_comp_troca", nullable = false)
	private Componentes componenteTroca;
	
	@Column(name = "servico_data")
	private LocalDateTime servicoData;
	
	
	
	
	
	//getters
	
		public Long getId() {
			return id;
		}
		
		public Maquina getMaquina() {
			return maquina;
		}
		
		public String getDescComponenteOrig() {
			return descCompOrig;
		}
		
		public Componentes getComponenteTroca() {
			return componenteTroca;
		}
		
		public LocalDateTime getServicoData() {
			return servicoData;
		}
		
		
		
		
		//setters
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public void setMaquina(Maquina maq) {
			this.maquina = maq;
		}
		
		public void setDescComponenteOrig(String desc) {
			this.descCompOrig = desc;
		}
		
		public void setComponenteTroca(Componentes comp) {
			this.componenteTroca = comp;
		}
		
		public void setServicoData(LocalDateTime data) {
			this.servicoData = data;
		}
}