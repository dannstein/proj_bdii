package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Apontamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApontamentoRepository extends JpaRepository<Apontamento, Long>{
	
	List<Apontamento> findByServico_Id(Long servicoId);
	
	List<Apontamento> findByFuncionario_Id(Long funcionarioId);
	
	
	
}