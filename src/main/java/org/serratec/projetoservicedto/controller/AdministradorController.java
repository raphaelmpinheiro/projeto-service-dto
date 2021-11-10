package org.serratec.projetoservicedto.controller;

import java.net.URI;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.dto.UsuarioDTO;
import org.serratec.projetoservicedto.dto.UsuarioInserirDTO;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.serratec.projetoservicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/usuario")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Object> criar(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {

		Usuario usuario = usuarioInserirDTO.createUsuario();
		usuario.setFuncao("ROLE_admin");
		usuario = usuarioService.inserir(usuario);

		// Gerar uma URI /api/usuario/{id} - valor do Id que foi criado.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

	}

}
