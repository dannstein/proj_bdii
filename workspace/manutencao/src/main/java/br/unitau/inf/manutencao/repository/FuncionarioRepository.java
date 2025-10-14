package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	
	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByNomeIgnoreCase(String nome);
	
	List<Funcionario> findByNomeContaining(String nome);
	
	List<Funcionario> findByNomeContainingIgnoreCase(String nome);
	
	List<Funcionario> findByCpf(String cpf);
	
	List<Funcionario> findByEmailPessoal(String emailPessoal);
	
	List<Funcionario> finnByEmailCorp(String emailCorp);
	
}