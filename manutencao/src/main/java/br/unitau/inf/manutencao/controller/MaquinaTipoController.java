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

import br.unitau.inf.manutencao.dto.MaquinaTipoGetDTO;
import br.unitau.inf.manutencao.dto.MaquinaTipoPostDTO;
import br.unitau.inf.manutencao.dto.MaquinaTipoPutDTO;
import br.unitau.inf.manutencao.model.MaquinaTipo;
import br.unitau.inf.manutencao.repository.MaquinaTipoRepository;


@RestController
@RequestMapping("maquinatipo")
public class MaquinaTipoController {
	@Autowired
	private MaquinaTipoRepository repository;


    @GetMapping
    public List<MaquinaTipoGetDTO> get() {
    	List<MaquinaTipo> lista = repository.findAll();
    	return MaquinaTipoGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinaTipo> getById(@PathVariable Long id) {
    	ResponseEntity<MaquinaTipo> ret = ResponseEntity.notFound().build();
    	Optional<MaquinaTipo> search = repository.findById(id);
    	if (search.isPresent()) {
    		MaquinaTipo item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Aluno nao encontrado");
    	return ret;
    }

	@PostMapping
	@Transactional
	public ResponseEntity<MaquinaTipoGetDTO> post(@RequestBody @Valid MaquinaTipoPostDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<MaquinaTipoGetDTO> ret = ResponseEntity.unprocessableEntity().build();
		MaquinaTipo item = body.convert();
		Optional<MaquinaTipo> search = repository.findByNome(item.getNome());
		if (!search.isPresent()) {
			repository.save(item);
			URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(item.getId()).toUri();
			ret = ResponseEntity.created(uri).body(new MaquinaTipoGetDTO(item));
		} else
			System.out.println("Nome do aluno ja existente");
		return ret;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MaquinaTipoGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid MaquinaTipoPutDTO body, UriComponentsBuilder uriBuilder) {
		ResponseEntity<MaquinaTipoGetDTO> ret = ResponseEntity.notFound().build();
		Optional<MaquinaTipo> search = repository.findById(id);
		if (search.isPresent()) {
			MaquinaTipo item = search.get();
			boolean found = false;
			Optional<MaquinaTipo> other = repository.findByNome(body.getNome());
			if (other.isPresent()) {
				MaquinaTipo old = other.get();
				if (old.getId()!=item.getId()) {
					found = true;
					System.out.println("Nome do MaquinaTipo ja existente");
					ret = ResponseEntity.unprocessableEntity().build();
				}
			}
			if (!found) {
				body.update(item);				
				URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(item.getId()).toUri();
				ret = ResponseEntity.created(uri).body(new MaquinaTipoGetDTO(item));
			}
		} else
			System.out.println("Aluno nao encontrado");
		return ret;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<MaquinaTipo> ret = ResponseEntity.notFound().build();
		Optional<MaquinaTipo> search = repository.findById(id);
		if (search.isPresent()) {
			MaquinaTipo item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("MaquinaTipo nao encontrado");
		return ret;
	}
}