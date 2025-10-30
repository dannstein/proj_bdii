package br.unitau.inf.manutencao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

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

import br.unitau.inf.manutencao.dto.TurnoGetDTO;
import br.unitau.inf.manutencao.dto.TurnoPostDTO;
import br.unitau.inf.manutencao.dto.TurnoPutDTO;
import br.unitau.inf.manutencao.model.Turno;
import br.unitau.inf.manutencao.repository.TurnoRepository;


@RestController
@RequestMapping("turno")
public class TurnoController {
	@Autowired
	private TurnoRepository repository;


    @GetMapping
    public List<TurnoGetDTO> get() {
    	List<Turno> lista = repository.findAll();
    	return TurnoGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getById(@PathVariable Long id) {
    	ResponseEntity<Turno> ret = ResponseEntity.notFound().build();
    	Optional<Turno> search = repository.findById(id);
    	if (search.isPresent()) {
    		Turno item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Turno nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TurnoGetDTO> post(@RequestBody @Valid TurnoPostDTO body, UriComponentsBuilder uriBuilder) {
        Turno item = body.convert();

        if (repository.findByEntradaAndSaida(item.getEntrada(), item.getSaida()).isPresent()) {
            System.out.println("Já existe um turno com o mesmo horário de entrada e saída");
            return ResponseEntity.unprocessableEntity().build();
        }

        repository.save(item);

        URI uri = uriBuilder.path("/turno/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurnoGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TurnoGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid TurnoPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Turno> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Turno não encontrado");
            return ResponseEntity.notFound().build();
        }

        Turno item = search.get();

        if (repository.existsByEntradaAndSaidaExceptId(body.getEntrada(), body.getSaida(), id)) {
            System.out.println("Já existe outro turno com o mesmo horário de entrada e saída");
            return ResponseEntity.unprocessableEntity().build();
        }

        body.update(item);
        repository.save(item);

        URI uri = uriBuilder.path("/turno/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.ok(new TurnoGetDTO(item));
    }
	




	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<TurnoGetDTO> patch(@PathVariable("id") Long id, @RequestBody TurnoPutDTO body) {
	    Optional<Turno> search = repository.findById(id);
	
	    if (search.isEmpty()) {
	        System.out.println("Turno não encontrado");
	        return ResponseEntity.notFound().build();
	    }
	
	    Turno item = search.get();
	
	    LocalTime novaEntrada = body.getEntrada() != null ? body.getEntrada() : item.getEntrada();
	    LocalTime novaSaida = body.getSaida() != null ? body.getSaida() : item.getSaida();
	
	    boolean sobreposto = repository.findAll().stream()
	        .filter(t -> !t.getId().equals(id))
	        .anyMatch(t -> novaEntrada.equals(t.getEntrada()) && novaSaida.equals(t.getSaida()));
	
	    if (sobreposto) {
	        System.out.println("Já existe um turno com esse horário de entrada e saída");
	        return ResponseEntity.unprocessableEntity().build();
	    }
	
	    body.update(item);
	    repository.save(item);
	
	    return ResponseEntity.ok(new TurnoGetDTO(item));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Turno> ret = ResponseEntity.notFound().build();
		Optional<Turno> search = repository.findById(id);
		if (search.isPresent()) {
			Turno item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Turno nao encontrado");
		return ret;
	}
}