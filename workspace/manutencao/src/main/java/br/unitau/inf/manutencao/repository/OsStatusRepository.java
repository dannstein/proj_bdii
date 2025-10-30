package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.OsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OsStatusRepository extends JpaRepository<OsStatus, Long>{
	Optional<OsStatus>  findByTipo(String tipo);
	
}