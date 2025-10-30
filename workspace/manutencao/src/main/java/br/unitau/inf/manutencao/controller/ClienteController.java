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

import br.unitau.inf.manutencao.dto.ClienteGetDTO;
import br.unitau.inf.manutencao.dto.ClientePostDTO;
import br.unitau.inf.manutencao.dto.ClientePutDTO;
import br.unitau.inf.manutencao.model.Cliente;
import br.unitau.inf.manutencao.repository.ClienteRepository;


@RestController
@RequestMapping("cliente")
public class ClienteController {
	@Autowired
	private ClienteRepository repository;


    @GetMapping
    public List<ClienteGetDTO> get() {
    	List<Cliente> lista = repository.findAll();
    	return ClienteGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
    	ResponseEntity<Cliente> ret = ResponseEntity.notFound().build();
    	Optional<Cliente> search = repository.findById(id);
    	if (search.isPresent()) {
    		Cliente item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Cliente nao encontrado");
    	return ret;
    }

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteGetDTO> post(@RequestBody @Valid ClientePostDTO body, UriComponentsBuilder uriBuilder) {
		 Cliente item = body.convert();
		 
	    boolean exists = repository.existsByCpfOrEmailOrCelular(
	        item.getCpf(), item.getEmail(), item.getCelular()
	    );

	    if (exists) {
	        System.out.println("Já existe cliente com o mesmo CPF, e-mail ou celular");
	        return ResponseEntity.unprocessableEntity().build();
	    }

	    repository.save(item);

	    URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
	    return ResponseEntity.created(uri).body(new ClienteGetDTO(item));
	}	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid ClientePutDTO body, UriComponentsBuilder uriBuilder) {
		 Optional<Cliente> search = repository.findById(id);
		 
	    if (search.isEmpty()) {
	        System.out.println("Cliente não encontrado");
	        return ResponseEntity.notFound().build();
	    }

	    Cliente item = search.get();

	    boolean cpfEmUso = repository.findByCpf(body.getCpf())
	            .filter(c -> !c.getId().equals(id))
	            .isPresent();

	    boolean emailEmUso = repository.findByEmail(body.getEmail())
	            .filter(c -> !c.getId().equals(id))
	            .isPresent();

	    boolean celularEmUso = repository.findByCelular(body.getCelular())
	            .filter(c -> !c.getId().equals(id))
	            .isPresent();

	    if (cpfEmUso || emailEmUso || celularEmUso) {
	        System.out.println("CPF, e-mail ou celular já cadastrado em outro cliente");
	        return ResponseEntity.unprocessableEntity().build();
	    }

	    body.update(item);
	    repository.save(item); 

	    URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
	    return ResponseEntity.created(uri).body(new ClienteGetDTO(item));
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteGetDTO> patch(@PathVariable("id") Long id, @RequestBody ClientePutDTO body, UriComponentsBuilder uriBuilder) {

	    Optional<Cliente> search = repository.findById(id);

	    if (search.isEmpty()) {
	        System.out.println("Cliente não encontrado");
	        return ResponseEntity.notFound().build();
	    }

	    Cliente item = search.get();

	    if (body.getCpf() != null) {
	        boolean cpfEmUso = repository.findByCpf(body.getCpf())
	                .filter(c -> !c.getId().equals(id))
	                .isPresent();
	        if (cpfEmUso) {
	            System.out.println("CPF já cadastrado em outro cliente");
	            return ResponseEntity.unprocessableEntity().build();
	        }
	    }

	    if (body.getEmail() != null) {
	        boolean emailEmUso = repository.findByEmail(body.getEmail())
	                .filter(c -> !c.getId().equals(id))
	                .isPresent();
	        if (emailEmUso) {
	            System.out.println("E-mail já cadastrado em outro cliente");
	            return ResponseEntity.unprocessableEntity().build();
	        }
	    }

	    if (body.getCelular() != null) {
	        boolean celularEmUso = repository.findByCelular(body.getCelular())
	                .filter(c -> !c.getId().equals(id))
	                .isPresent();
	        if (celularEmUso) {
	            System.out.println("Celular já cadastrado em outro cliente");
	            return ResponseEntity.unprocessableEntity().build();
	        }
	    }

	    body.update(item);
	    repository.save(item);

	    URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
	    return ResponseEntity.created(uri).body(new ClienteGetDTO(item));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Cliente> ret = ResponseEntity.notFound().build();
		Optional<Cliente> search = repository.findById(id);
		if (search.isPresent()) {
			Cliente item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Cliente nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countClientes() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}