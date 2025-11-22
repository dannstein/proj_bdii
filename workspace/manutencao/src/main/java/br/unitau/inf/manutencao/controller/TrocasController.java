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

import br.unitau.inf.manutencao.dto.TrocasGetDTO;
import br.unitau.inf.manutencao.dto.TrocasPostDTO;
import br.unitau.inf.manutencao.dto.TrocasPutDTO;

import br.unitau.inf.manutencao.model.Troca;
import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.repository.ServicoRepository;
import br.unitau.inf.manutencao.repository.ComponenteRepository;
import br.unitau.inf.manutencao.repository.TrocaRepository;



@RestController
@RequestMapping("trocas")
public class TrocasController {
	@Autowired
	private TrocaRepository repository;
	
	@Autowired
	private ComponenteRepository componenteRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	


    @GetMapping
    public List<TrocasGetDTO> get() {
    	List<Troca> lista = repository.findAll();
    	return TrocasGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Troca> getById(@PathVariable Long id) {
    	ResponseEntity<Troca> ret = ResponseEntity.notFound().build();
    	Optional<Troca> search = repository.findById(id);
    	if (search.isPresent()) {
    		Troca item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Troca nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TrocasGetDTO> post(@RequestBody @Valid TrocasPostDTO body, UriComponentsBuilder uriBuilder) {
    	Componentes componente = componenteRepository.findById(body.getComponenteTrocaId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Servico servico = servicoRepository.findById(body.getServicoId())
    			 .orElseThrow(() -> new RuntimeException("Servico nao encontrado"));
    	 
        
        Troca item = body.convert(servico, componente);

        repository.save(item);

        URI uri = uriBuilder.path("/Troca/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new TrocasGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TrocasGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid TrocasPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Troca> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Troca não encontrada");
            return ResponseEntity.notFound().build();
        }

        Troca item = search.get();

        
        
        Componentes componente = componenteRepository.findById(body.getComponenteTrocaId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Servico servico = servicoRepository.findById(body.getServicoId())
    			 .orElseThrow(() -> new RuntimeException("Servico nao encontrado"));

        body.update(item, servico, componente);
        repository.save(item);

        URI uri = uriBuilder.path("/troca/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new TrocasGetDTO(item));
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Troca> ret = ResponseEntity.notFound().build();
		Optional<Troca> search = repository.findById(id);
		if (search.isPresent()) {
			Troca item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Troca nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countTroca() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}