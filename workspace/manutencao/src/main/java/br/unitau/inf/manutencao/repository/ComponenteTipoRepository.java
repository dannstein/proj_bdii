package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.ComponenteTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponenteTipoRepository extends JpaRepository<ComponenteTipo, Long>{
	
	
}