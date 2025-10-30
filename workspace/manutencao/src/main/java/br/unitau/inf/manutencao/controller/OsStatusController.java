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

import br.unitau.inf.manutencao.dto.OsStatusGetDTO;
import br.unitau.inf.manutencao.dto.OsStatusPostDTO;
import br.unitau.inf.manutencao.dto.OsStatusPutDTO;
import br.unitau.inf.manutencao.model.OsStatus;
import br.unitau.inf.manutencao.repository.OsStatusRepository;


@RestController
@RequestMapping("osstatus")
public class OsStatusController {
	@Autowired
	private OsStatusRepository repository;


    @GetMapping
    public List<OsStatusGetDTO> get() {
    	List<OsStatus> lista = repository.findAll();
    	return OsStatusGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OsStatus> getById(@PathVariable Long id) {
    	ResponseEntity<OsStatus> ret = ResponseEntity.notFound().build();
    	Optional<OsStatus> search = repository.findById(id);
    	if (search.isPresent()) {
    		OsStatus item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("OsStatus nao encontrado");
    	return ret;
    }

	@PostMapping
	@Transactional
	public ResponseEntity<OsStatusGetDTO> post(@RequestBody @Valid OsStatusPostDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<OsStatusGetDTO> ret = ResponseEntity.unprocessableEntity().build();
		OsStatus item = body.convert();
		Optional<OsStatus> search = repository.findByTipo(item.getTipo());
		if (!search.isPresent()) {
			repository.save(item);
			URI uri = uriBuilder.path("/OsStatus/{id}").buildAndExpand(item.getId()).toUri();
			ret = ResponseEntity.created(uri).body(new OsStatusGetDTO(item));
		} else
			System.out.println("OsStatus ja existente");
		return ret;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OsStatusGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid OsStatusPutDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<OsStatusGetDTO> ret = ResponseEntity.notFound().build();
		Optional<OsStatus> search = repository.findById(id);
		if (search.isPresent()) {
			OsStatus item = search.get();
			boolean found = false;
			Optional<OsStatus> other = repository.findByTipo(body.getTipo());
			if (other.isPresent()) {
				OsStatus old = other.get();
				if (old.getId()!=item.getId()) {
					found = true;
					System.out.println("Nome do OsStatus ja existente");
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if (!found) {
				body.update(item);				
				URI uri = uriBuilder.path("/OsStatus/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new OsStatusGetDTO(item));
			}
		} else
			System.out.println("OsStatus nao encontrado");
		return ret;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<OsStatus> ret = ResponseEntity.notFound().build();
		Optional<OsStatus> search = repository.findById(id);
		if (search.isPresent()) {
			OsStatus item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("OsStatus nao encontrado");
		return ret;
	}
}