package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long>{
	//metodos personalizados (findByModelo, etc..)
	//JpaRepository já herda findById, findAll, save, count, delete...
	
	List<Maquina> findByModelo(String modelo);
	
	List<Maquina> findByCliente_Id(Long clienteId);
	
	
}