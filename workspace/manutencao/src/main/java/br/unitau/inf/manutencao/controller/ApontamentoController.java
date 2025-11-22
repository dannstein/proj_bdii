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

import br.unitau.inf.manutencao.dto.ApontamentoGetDTO;
import br.unitau.inf.manutencao.dto.ApontamentoPostDTO;
import br.unitau.inf.manutencao.dto.ApontamentoPutDTO;

import br.unitau.inf.manutencao.model.Apontamento;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Funcionario;
import br.unitau.inf.manutencao.repository.ServicoRepository;
import br.unitau.inf.manutencao.repository.FuncionarioRepository;
import br.unitau.inf.manutencao.repository.ApontamentoRepository;



@RestController
@RequestMapping("apontamento")
public class ApontamentoController {
	@Autowired
	private ApontamentoRepository repository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	


    @GetMapping
    public List<ApontamentoGetDTO> get() {
    	List<Apontamento> lista = repository.findAll();
    	return ApontamentoGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apontamento> getById(@PathVariable Long id) {
    	ResponseEntity<Apontamento> ret = ResponseEntity.notFound().build();
    	Optional<Apontamento> search = repository.findById(id);
    	if (search.isPresent()) {
    		Apontamento item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Apontamento nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ApontamentoGetDTO> post(@RequestBody @Valid ApontamentoPostDTO body, UriComponentsBuilder uriBuilder) {
    	Funcionario funcionario = funcionarioRepository.findById(body.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Servico servico = servicoRepository.findById(body.getServicoId())
    			 .orElseThrow(() -> new RuntimeException("Servico nao encontrado"));
    	 
        
        Apontamento item = body.convert(servico, funcionario);

        repository.save(item);

        URI uri = uriBuilder.path("/Apontamento/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApontamentoGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApontamentoGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid ApontamentoPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Apontamento> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Apontamento não encontrada");
            return ResponseEntity.notFound().build();
        }

        Apontamento item = search.get();

        
        
        Funcionario funcionario = funcionarioRepository.findById(body.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Servico servico = servicoRepository.findById(body.getServicoId())
    			 .orElseThrow(() -> new RuntimeException("Servico nao encontrado"));

        body.update(item, servico, funcionario);
        repository.save(item);

        URI uri = uriBuilder.path("/Apontamento/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApontamentoGetDTO(item));
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Apontamento> ret = ResponseEntity.notFound().build();
		Optional<Apontamento> search = repository.findById(id);
		if (search.isPresent()) {
			Apontamento item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Apontamento nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countApontamento() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}