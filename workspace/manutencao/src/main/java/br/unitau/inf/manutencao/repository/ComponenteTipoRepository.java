package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.ComponenteTipo;
import br.unitau.inf.manutencao.model.MaquinaTipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponenteTipoRepository extends JpaRepository<ComponenteTipo, Long>{
	
	Optional<ComponenteTipo>  findByNome(String nome);
	
	boolean existsByNome(String nome);
	
	long countByNome(String nome);
	
	void deleteByNome(String nome);
	
}