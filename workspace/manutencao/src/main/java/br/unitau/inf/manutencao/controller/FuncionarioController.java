package br.unitau.inf.manutencao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.unitau.inf.manutencao.dto.FuncionarioGetDTO;
import br.unitau.inf.manutencao.dto.FuncionarioPostDTO;
import br.unitau.inf.manutencao.dto.FuncionarioPutDTO;
import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.repository.FuncionarioRepository;

import br.unitau.inf.manutencao.model.Turno;
import br.unitau.inf.manutencao.repository.TurnoRepository;

@RestController
@RequestMapping("Funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private TurnoRepository turnoRepository;


    @GetMapping
    public List<FuncionarioGetDTO> get() {
    	List<Funcionario> lista = repository.findAll();
    	return FuncionarioGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
    	ResponseEntity<Funcionario> ret = ResponseEntity.notFound().build();
    	Optional<Funcionario> search = repository.findById(id);
    	if (search.isPresent()) {
    		Funcionario item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Funcionario nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FuncionarioGetDTO> post(@RequestBody @Valid FuncionarioPostDTO body, UriComponentsBuilder uriBuilder) {
    	 Turno turno = turnoRepository.findById(body.getTurnoId())
    		        .orElseThrow(() -> new RuntimeException("Turno não encontrado"));
        
        Funcionario item = body.convert(turno);

        boolean cpfEmUso = repository.existsActiveByCpf(item.getCpf());
        if (cpfEmUso) {
            System.out.println("Já existe um funcionário ativo com o mesmo CPF");
            return ResponseEntity.unprocessableEntity().build();
        }
        
        if (item.getEmailCorp() != null && repository.existsByEmailCorp(item.getEmailCorp())) {
        	System.out.println("E-mail corporativo já cadastrado");
            return ResponseEntity.unprocessableEntity().build();
        }
        

        repository.save(item);

        URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FuncionarioGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid FuncionarioPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Funcionario> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Funcionário não encontrado");
            return ResponseEntity.notFound().build();
        }

        Funcionario item = search.get();

        if (body.getCpf() != null) {
            boolean cpfAtivoEmUso = repository.existsActiveByCpfExceptId(body.getCpf(), id);
            if (cpfAtivoEmUso) {
                System.out.println("Já existe um funcionário ativo com o mesmo CPF");
                return ResponseEntity.unprocessableEntity().build();
            }
        }


        if (body.getEmailCorp() != null) {
            boolean emailCorpEmUso = repository.existsByEmailCorpExceptId(body.getEmailCorp(), id);
            if (emailCorpEmUso) {
                System.out.println("E-mail corporativo já cadastrado em outro funcionário");
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        
        Turno turno = turnoRepository.findById(body.getTurnoId())
		        .orElseThrow(() -> new RuntimeException("Turno não encontrado"));

        // Atualiza os dados do funcionário
        body.update(item, turno);
        repository.save(item);

        URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioGetDTO(item));
    }

	
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<FuncionarioGetDTO> patch(
            @PathVariable("id") Long id,
            @RequestBody FuncionarioPutDTO body,
            UriComponentsBuilder uriBuilder) {

        Optional<Funcionario> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Funcionário não encontrado");
            return ResponseEntity.notFound().build();
        }

        Funcionario item = search.get();

        if (body.getCpf() != null) {
            boolean cpfAtivoEmUso = repository.existsActiveByCpfExceptId(body.getCpf(), id);
            if (cpfAtivoEmUso) {
                System.out.println("Já existe um funcionário ativo com o mesmo CPF");
                return ResponseEntity.unprocessableEntity().build();
            }
        }

        if (body.getEmailCorp() != null) {
            boolean emailCorpEmUso = repository.existsByEmailCorpExceptId(body.getEmailCorp(), id);
            if (emailCorpEmUso) {
                System.out.println("E-mail corporativo já cadastrado em outro funcionário");
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        
        Turno turno = turnoRepository.findById(body.getTurnoId())
		        .orElseThrow(() -> new RuntimeException("Turno não encontrado"));

        body.update(item, turno);
        repository.save(item);

        URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioGetDTO(item));
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Funcionario> ret = ResponseEntity.notFound().build();
		Optional<Funcionario> search = repository.findById(id);
		if (search.isPresent()) {
			Funcionario item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Funcionario nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countFuncionarios() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}