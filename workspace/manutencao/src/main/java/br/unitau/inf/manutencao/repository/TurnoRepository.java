package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
	
	List<Turno> findByTipo(String tipo);
	
	List<Turno> findByTipoIgnoreCase(String tipo);
	
	List<Turno> findByTipoContaining(String tipo);
	
	List<Turno> findByTipoContainingIgnoreCase(String tipo);
	
	
	
}