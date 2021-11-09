package org.serratec.projetoservicedto.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.dto.UsuarioDTO;
import org.serratec.projetoservicedto.dto.UsuarioInserirDTO;
import org.serratec.projetoservicedto.exception.EmailException;
import org.serratec.projetoservicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping		
	public ResponseEntity<Object> get() {
		List<Usuario> usuarios = usuarioService.listar();		
		return ResponseEntity.ok(UsuarioDTO.convert(usuarios));
	}
	

	@GetMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Object> get(@PathVariable("id") long id) {
		Optional<Usuario> usuario = usuarioService.getById(id);
		
		if(usuario.isPresent())
		{
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));	
		}
		
		return ResponseEntity.notFound().build();		
	}

	@PostMapping
	// Retornar 201 - Created
	// Retornar 422 - Não foi possivel processar a requisição.
	@PreAuthorize("hasRole('admin') && hasRole('usuario')")
	public ResponseEntity<Object> criar(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {

		Usuario usuario = usuarioInserirDTO.createUsuario();
		usuario = usuarioService.inserir(usuario);
		// Gerar uma URI /api/usuario/{id} - valor do Id que foi criado.
		URI uri = 
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

	}
}
