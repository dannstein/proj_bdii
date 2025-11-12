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

import br.unitau.inf.manutencao.dto.MaquinaGetDTO;
import br.unitau.inf.manutencao.dto.MaquinaPostDTO;
import br.unitau.inf.manutencao.dto.MaquinaPutDTO;
import br.unitau.inf.manutencao.dto.MaquinaPatchDTO;
import br.unitau.inf.manutencao.model.Maquina;
import br.unitau.inf.manutencao.repository.MaquinaRepository;

import br.unitau.inf.manutencao.model.MaquinaTipo;
import br.unitau.inf.manutencao.repository.MaquinaTipoRepository;

import br.unitau.inf.manutencao.model.Cliente;
import br.unitau.inf.manutencao.repository.ClienteRepository;



@RestController
@RequestMapping("maquina")
public class MaquinaController {
	@Autowired
	private MaquinaRepository repository;
	
	@Autowired
	private MaquinaTipoRepository maquinaTipoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;


    @GetMapping
    public List<MaquinaGetDTO> get() {
    	List<Maquina> lista = repository.findAll();
    	return MaquinaGetDTO.convert(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> getById(@PathVariable Long id) {
    	ResponseEntity<Maquina> ret = ResponseEntity.notFound().build();
    	Optional<Maquina> search = repository.findById(id);
    	if (search.isPresent()) {
    		Maquina item = search.get();
    		ret = ResponseEntity.ok(item);
    	} else
    		System.out.println("Maquina nao encontrado");
    	return ret;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MaquinaGetDTO> post(@RequestBody @Valid MaquinaPostDTO body, UriComponentsBuilder uriBuilder) {
    	 MaquinaTipo maquinaTipo = maquinaTipoRepository.findById(body.getMaquinaTipoId())
    		        .orElseThrow(() -> new RuntimeException("Tipo de maquina não encontrado"));
    	 
    	 Cliente cliente = clienteRepository.findById(body.getClienteId())
    			 .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Maquina item = body.convert(maquinaTipo, cliente);

        repository.save(item);

        URI uri = uriBuilder.path("/maquina/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new MaquinaGetDTO(item));
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MaquinaGetDTO> put(@PathVariable("id") Long id, @RequestBody @Valid MaquinaPutDTO body, UriComponentsBuilder uriBuilder) {
        Optional<Maquina> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Mauqina não encontrada");
            return ResponseEntity.notFound().build();
        }

        Maquina item = search.get();

        
        
        MaquinaTipo maquinaTipo = maquinaTipoRepository.findById(body.getMaquinaTipoId())
		        .orElseThrow(() -> new RuntimeException("Tipo de maquina não encontrado"));
	 
	 Cliente cliente = clienteRepository.findById(body.getClienteId())
			 .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza os dados do funcionário
        body.update(item, maquinaTipo, cliente);
        repository.save(item);

        URI uri = uriBuilder.path("/maquina/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new MaquinaGetDTO(item));
    }

	
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<MaquinaGetDTO> patch(
            @PathVariable("id") Long id,
            @RequestBody MaquinaPatchDTO body,
            UriComponentsBuilder uriBuilder) {

        Optional<Maquina> search = repository.findById(id);

        if (search.isEmpty()) {
            System.out.println("Maquina não encontrada");
            return ResponseEntity.notFound().build();
        }

        Maquina item = search.get();

        
        
        MaquinaTipo maquinaTipo = maquinaTipoRepository.findById(body.getMaquinaTipoId())
		        .orElseThrow(() -> new RuntimeException("Tipo de maquina não encontrado"));
	 
        Cliente cliente = clienteRepository.findById(body.getClienteId())
			 .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        body.update(item, maquinaTipo, cliente);
        repository.save(item);

        URI uri = uriBuilder.path("/maquina/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new MaquinaGetDTO(item));
    }


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseEntity<Maquina> ret = ResponseEntity.notFound().build();
		Optional<Maquina> search = repository.findById(id);
		if (search.isPresent()) {
			Maquina item = search.get();
			
			repository.delete(item);
			ret = ResponseEntity.ok().build();
			
		} else
			System.out.println("Maquina nao encontrado");
		return ret;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countMaquinas() {
	    long total = repository.count();
	    return ResponseEntity.ok(total);
	}
}