package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.time.LocalTime;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
	
	Optional<Turno> findByTipo(String tipo);
	
	Optional<Turno> findByEntradaAndSaida(LocalTime entrada, LocalTime saida);
	
	default boolean existsByEntradaAndSaidaExceptId(LocalTime entrada, LocalTime saida, Long id) {
        return findByEntradaAndSaida(entrada, saida)
                .filter(turno -> !turno.getId().equals(id))
                .isPresent();
    }
	
	
	
}