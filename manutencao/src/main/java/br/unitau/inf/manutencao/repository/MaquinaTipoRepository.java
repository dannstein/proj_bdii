package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.MaquinaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


import java.util.List;

@Repository
public interface MaquinaTipoRepository extends JpaRepository<MaquinaTipo, Long>{
	//metodos personalizados (findByModelo, etc..)
	//JpaRepository já herda findById, findAll, save, count, delete...
	
	Optional<MaquinaTipo>  findByNome(String nome);
	
	boolean existsByNome(String nome);
	
	long countByNome(String nome);
	
	void deleteByNome(String nome);
	
	
	
	
}