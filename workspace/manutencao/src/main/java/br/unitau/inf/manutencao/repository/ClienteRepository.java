package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
	Optional<Cliente> findByNome(String nome);
	
	Optional<Cliente> findByCpf(String cpf);
	
	Optional<Cliente> findByCelular(String celular);
	
	Optional<Cliente> findByEmail(String email);
	
	boolean existsByCpfOrEmailOrCelular(String cpf, String email, String celular);
}