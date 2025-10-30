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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.unitau.inf.manutencao.dto.ComponenteTipoGetDTO;
import br.unitau.inf.manutencao.dto.ComponenteTipoPostDTO;
import br.unitau.inf.manutencao.dto.ComponenteTipoPutDTO;
import br.unitau.inf.manutencao.model.ComponenteTipo;
import br.unitau.inf.manutencao.repository.ComponenteTipoRepository;


@RestController
@RequestMapping("ComponenteTipo")
public class ComponenteTipoController {
	@Autowired
	private ComponenteTipoRepository repository;


    @GetMapping
    public List<ComponenteTipoGetDTO> get() {
    	List<ComponenteTipo> lista = repository.findAll();
    	return ComponenteTipoGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponenteTipo> getById(@PathVariable Long id) {
    	ResponseEntity<ComponenteTipo> ret = ResponseEntity.notFound().build();
    	Optional<ComponenteTipo> search = repository.findById(id);
    	if (search.isPresent()) {
    		ComponenteTipo item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("ComponenteTipo nao encontrado");
    	return ret;
    }

	@PostMapping
	@Transactional
	public ResponseEntity<ComponenteTipoGetDTO> post(@RequestBody @Valid ComponenteTipoPostDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<ComponenteTipoGetDTO> ret = ResponseEntity.unprocessableEntity().build();
		ComponenteTipo item = body.convert();
		Optional<ComponenteTipo> search = repository.findByNome(item.getNome());
		if (!search.isPresent()) {
			repository.save(item);
			URI uri = uriBuilder.path("/ComponenteTipo/{id}").buildAndExpand(item.getId()).toUri();
			ret = ResponseEntity.created(uri).body(new ComponenteTipoGetDTO(item));
		} else
			System.out.println("ComponenteTipo ja existente");
		return ret;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ComponenteTipoGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid ComponenteTipoPutDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<ComponenteTipoGetDTO> ret = ResponseEntity.notFound().build();
		Optional<ComponenteTipo> search = repository.findById(id);
		if (search.isPresent()) {
			ComponenteTipo item = search.get();
			boolean found = false;
			Optional<ComponenteTipo> other = repository.findByNome(body.getNome());
			if (other.isPresent()) {
				ComponenteTipo old = other.get();
				if (old.getId()!=item.getId()) {
					found = true;
					System.out.println("Nome do ComponenteTipo ja existente");
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if (!found) {
				body.update(item);				
				URI uri = uriBuilder.path("/ComponenteTipo/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new ComponenteTipoGetDTO(item));
			}
		} else
			System.out.println("ComponenteTipo nao encontrado");
		return ret;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<ComponenteTipo> ret = ResponseEntity.notFound().build();
		Optional<ComponenteTipo> search = repository.findById(id);
		if (search.isPresent()) {
			ComponenteTipo item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("ComponenteTipo nao encontrado");
		return ret;
	}
}