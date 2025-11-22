package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long>{
	//metodos personalizados (findByModelo, etc..)
	//JpaRepository já herda findById, findAll, save, count, delete...
	
	Optional<Maquina> findByModelo(String modelo);
	
	Optional<Maquina> findByCliente_Id(Long clienteId);
	
	Optional<Maquina> findByNumserie(String ns);
	
	List<Maquina> findAllByCliente_Cpf(String cpf);
	
	
}