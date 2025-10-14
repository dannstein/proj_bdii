package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long>{
	
	List<Componente> findByNome(String nome);
	
	List<Componente> findByNomeIgnoreCase(String nome);
	
	List<Componente> findByNomeContaining(String nome);
	
	List<Componente> findByNomeContainingIgnoreCase(String nome);
	
	List<Componente> findByComponenteTipo_Id(Long tipoId);
	
	List<Componente> findByComponenteTipo_Nome(String nomeTipo);
	
	List<Componente> findByComponenteTipo_NomeIgnoreCase(String nomeTipo);
}