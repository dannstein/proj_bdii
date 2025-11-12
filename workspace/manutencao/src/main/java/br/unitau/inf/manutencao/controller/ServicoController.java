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

import br.unitau.inf.manutencao.dto.ServicoGetDTO;
import br.unitau.inf.manutencao.dto.ServicoPostDTO;
import br.unitau.inf.manutencao.dto.ServicoPutDTO;
import br.unitau.inf.manutencao.dto.ServicoPatchDTO;


import br.unitau.inf.manutencao.model.Servico;
import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.model.Componentes;

import br.unitau.inf.manutencao.repository.ServicoRepository;
import br.unitau.inf.manutencao.repository.ComponenteRepository;
import br.unitau.inf.manutencao.repository.MaquinaRepository;



@RestController
@RequestMapping("servico")
public class ServicoController {
	@Autowired
	private ServicoRepository repository;
	
	@Autowired
	private ComponenteRepository componenteRepository;
	
	@Autowired
	private MaquinaRepository maquinaRepository;
	


    @GetMapping
    public List<ServicoGetDTO> get() {
    	List<Servico> lista = repository.findAll();
    	return ServicoGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getById(@PathVariable Long id) {
    	ResponseEntity<Servico> ret = ResponseEntity.notFound().build();
    	Optional<Servico> search = repository.findById(id);
    	if (search.isPresent()) {
    		Servico item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Servico nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ServicoGetDTO> post(@RequestBody @Valid ServicoPostDTO body, UriComponentsBuilder uriBuilder) {
    	Componentes componente = componenteRepository.findById(body.getComponenteTrocaId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Maquina maquina = maquinaRepository.findById(body.getMaquinaId())
    			 .orElseThrow(() -> new RuntimeException("Mauqina nao encontrada"));
    	 
        
        Servico item = body.convert(maquina, componente);

        repository.save(item);

        URI uri = uriBuilder.path("/servico/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ServicoGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ServicoGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid ServicoPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Servico> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Servico não encontrada");
            return ResponseEntity.notFound().build();
        }

        Servico item = search.get();

        
        
        Componentes componente = componenteRepository.findById(body.getComponenteTrocaId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Maquina maquina = maquinaRepository.findById(body.getMaquinaId())
    			 .orElseThrow(() -> new RuntimeException("Mauqina nao encontrada"));
        // Atualiza os dados do funcionário
        body.update(item, maquina, componente);
        repository.save(item);

        URI uri = uriBuilder.path("/Servico/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ServicoGetDTO(item));
    }

	
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ServicoGetDTO> patch(
            @PathVariable("id") Long id,
            @RequestBody ServicoPatchDTO body) {

        Optional<Servico> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Servico não encontrado");
            return ResponseEntity.notFound().build();
        }

        Servico item = search.get();

        Componentes componente = componenteRepository.findById(body.getComponenteTrocaId())
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
    	 
    	 Maquina maquina = maquinaRepository.findById(body.getMaquinaId())
    			 .orElseThrow(() -> new RuntimeException("Mauqina nao encontrada"));

        body.update(item, maquina, componente);
        repository.saveAndFlush(item); // força atualização imediata

        return ResponseEntity.ok(new ServicoGetDTO(item)); // 200 OK
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Servico> ret = ResponseEntity.notFound().build();
		Optional<Servico> search = repository.findById(id);
		if (search.isPresent()) {
			Servico item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Servico nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countServico() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}