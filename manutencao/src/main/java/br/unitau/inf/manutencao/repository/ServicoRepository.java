package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
	
	List<Servico> findByMaquina_Id(Long maquinaId);
	
	
}