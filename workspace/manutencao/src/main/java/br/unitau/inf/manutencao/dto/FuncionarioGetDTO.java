package br.unitau.inf.manutencao.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Turno;

import br.unitau.inf.manutencao.dto.TurnoGetDTO;


public class FuncionarioGetDTO{
	private Long id;
	private String nome;
	private String celular;
	private String cpf;
	private String emailPessoal;
	private String emailCorp;
	private LocalDate ingresso;
	private LocalDate demissao;
	
	private TurnoGetDTO turno;
	
	
	public FuncionarioGetDTO() {
		
	}
	
	 public FuncionarioGetDTO(Funcionario tipo) {
	        this.id = tipo.getId();
	        this.nome = tipo.getNome();
	        this.celular = tipo.getCelular();
	        this.cpf = tipo.getCpf();
	        this.emailPessoal = tipo.getEmailPessoal();
	        this.emailCorp = tipo.getEmailCorp();
	        this.ingresso = tipo.getIngresso();
	        this.demissao = tipo.getDemissao();
	        this.turno = new TurnoGetDTO(tipo.getTurno());
	       }
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getEmailPessoal() {
		return emailPessoal;
	}
	
	public String getEmailCorp() {
		return emailCorp;
	}
	
	public LocalDate getIngresso() {
		return ingresso;
	}
	
	public LocalDate getDemissao() {
		return demissao;
	}
	
	public TurnoGetDTO getTurno() {
		return turno;
	}
	
	
	public static List<FuncionarioGetDTO> convert(List<Funcionario> lista){
		return lista.stream().map(FuncionarioGetDTO::new).collect(Collectors.toList());
	}
}
