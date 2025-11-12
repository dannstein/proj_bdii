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

import br.unitau.inf.manutencao.dto.ComponentesGetDTO;
import br.unitau.inf.manutencao.dto.ComponentesPostDTO;
import br.unitau.inf.manutencao.dto.ComponentesPutDTO;
import br.unitau.inf.manutencao.dto.ComponentesPatchDTO;
import br.unitau.inf.manutencao.model.Componentes;
import br.unitau.inf.manutencao.repository.ComponenteRepository;

import br.unitau.inf.manutencao.model.ComponenteTipo;
import br.unitau.inf.manutencao.repository.ComponenteTipoRepository;



@RestController
@RequestMapping("componentes")
public class ComponentesController {
	@Autowired
	private ComponenteRepository repository;
	
	@Autowired
	private ComponenteTipoRepository ComponentesTipoRepository;
	


    @GetMapping
    public List<ComponentesGetDTO> get() {
    	List<Componentes> lista = repository.findAll();
    	return ComponentesGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Componentes> getById(@PathVariable Long id) {
    	ResponseEntity<Componentes> ret = ResponseEntity.notFound().build();
    	Optional<Componentes> search = repository.findById(id);
    	if (search.isPresent()) {
    		Componentes item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Componente nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ComponentesGetDTO> post(@RequestBody @Valid ComponentesPostDTO body, UriComponentsBuilder uriBuilder) {
    	 ComponenteTipo componenteTipo = ComponentesTipoRepository.findById(body.getComponenteTipoId())
    		        .orElseThrow(() -> new RuntimeException("Tipo de Componentes não encontrado"));
    	 
        
        Componentes item = body.convert(componenteTipo);

        repository.save(item);

        URI uri = uriBuilder.path("/componentes/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ComponentesGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ComponentesGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid ComponentesPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Componentes> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Componente não encontrada");
            return ResponseEntity.notFound().build();
        }

        Componentes item = search.get();

        
        
        ComponenteTipo componenteTipo = ComponentesTipoRepository.findById(body.getComponenteTipoId())
		        .orElseThrow(() -> new RuntimeException("Tipo de Componentes não encontrado"));

        // Atualiza os dados do funcionário
        body.update(item, componenteTipo);
        repository.save(item);

        URI uri = uriBuilder.path("/componentes/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ComponentesGetDTO(item));
    }

	
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ComponentesGetDTO> patch(
            @PathVariable("id") Long id,
            @RequestBody ComponentesPatchDTO body) {

        Optional<Componentes> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Componente não encontrado");
            return ResponseEntity.notFound().build();
        }

        Componentes item = search.get();

        ComponenteTipo componenteTipo = null;
        if (body.getComponenteTipoId() != null) {
            componenteTipo = ComponentesTipoRepository.findById(body.getComponenteTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de Componente não encontrado"));
        }

        body.update(item, componenteTipo);
        repository.saveAndFlush(item); // força atualização imediata

        return ResponseEntity.ok(new ComponentesGetDTO(item)); // 200 OK
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Componentes> ret = ResponseEntity.notFound().build();
		Optional<Componentes> search = repository.findById(id);
		if (search.isPresent()) {
			Componentes item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Componentes nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countComponentes() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}