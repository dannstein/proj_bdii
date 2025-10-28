package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByNomeIgnoreCase(String nome);
	
	List<Cliente> findByNomeContaining(String nome);
	
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	List<Cliente> findByCpf(String cpf);
	
	List<Cliente> findByCelular(String celular);
	
	List<Cliente> findByEmail(String email);
	
}