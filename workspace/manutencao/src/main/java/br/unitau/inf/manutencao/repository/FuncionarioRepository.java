package br.unitau.inf.manutencao.repository;

import br.unitau.inf.manutencao.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
	Optional<Funcionario> findByEmailCorp(String emailCorp);
	
	@Query(value = """
	        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
	        FROM funcionario f
	        WHERE f.cpf = :cpf
	          AND f.demissao IS NULL
	        """, nativeQuery = true)
	boolean existsActiveByCpf(@Param("cpf") String cpf);
	
	@Query(value = """
	        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
	        FROM funcionario f
	        WHERE f.cpf = :cpf
	          AND f.demissao IS NULL
	          AND f.id <> :id
	        """, nativeQuery = true)
	boolean existsActiveByCpfExceptId(@Param("cpf") String cpf, @Param("id") Long id);
	
	 @Query(value = """
		        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
		        FROM funcionario f
		        WHERE f.emailcorp = :emailCorp
		        """, nativeQuery = true)
	boolean existsByEmailCorp(@Param("emailCorp") String emailCorp);
	 
	 @Query(value = """
		        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
		        FROM funcionario f
		        WHERE f.emailcorp = :emailCorp
		          AND f.id <> :id
		        """, nativeQuery = true)
	boolean existsByEmailCorpExceptId(@Param("emailCorp") String emailCorp, @Param("id") Long id);
	
	
}