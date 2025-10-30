package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Componentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ComponenteRepository extends JpaRepository<Componentes, Long>{
	
	List<Componentes> findByNome(String nome);
	

}