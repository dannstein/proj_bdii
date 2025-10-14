package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Troca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long>{
	
	List<Troca> findByServico_Id(Long servicoId);
	
	List<Troca> findByComponente_Id(Long componenteId);
	
	
	
	
}