package org.serratec.projetoservicedto.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.util.Strings;
import org.serratec.projetoservicedto.dominio.Foto;
import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.dto.FotoDTO;
import org.serratec.projetoservicedto.dto.UsuarioDTO;
import org.serratec.projetoservicedto.dto.UsuarioInserirDTO;
import org.serratec.projetoservicedto.exception.EmailException;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.serratec.projetoservicedto.service.FotoService;
import org.serratec.projetoservicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	FotoService fotoService;

	@GetMapping
	@PreAuthorize("hasRole('usuario')")
	public ResponseEntity<Object> get() {
		List<Usuario> usuarios = usuarioService.listar();
		return ResponseEntity.ok(UsuarioDTO.convert(usuarios));
	}

	@GetMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Object> get(@PathVariable("id") long id, 
			@AuthenticationPrincipal UserDetails user) {

		
		//Pega todas as roles do usuário.
		System.out.println(user.getAuthorities());		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		Optional<Usuario> usuario = usuarioService.getById(id);

		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	// Retornar 201 - Created
	// Retornar 422 - Não foi possivel processar a requisição.
	//@PreAuthorize("hasRole('admin') && hasRole('usuario')")
	public ResponseEntity<Object> criar(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {

		Usuario usuario = usuarioInserirDTO.createUsuario();
		usuario.setFuncao("ROLE_usuario");
		usuario = usuarioService.inserir(usuario);

		// Gerar uma URI /api/usuario/{id} - valor do Id que foi criado.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

	}
	
	@PostMapping("{id}/foto")
	public ResponseEntity<FotoDTO> salvarFoto(@PathVariable long id, @RequestParam MultipartFile file){
		Optional<Usuario> usuario = usuarioService.getById(id);
		if(usuario.isPresent()) {
			try {
				Foto foto = fotoService.inserir(usuario.get(), file);	
				FotoDTO fotodto = new FotoDTO(foto, usuario.get());
				return ResponseEntity.ok(fotodto);				
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}			
		}		
		return ResponseEntity.badRequest().build();
		
	}

	// api/usuario/123231/foto - retorna a imagem
	@GetMapping("{id}/foto")
	public ResponseEntity<byte[]> buscarPorFoto(@PathVariable long id) {
		Foto foto = fotoService.buscar(id);
		if (foto != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type", foto.getTipo());
			headers.add("content-length", String.valueOf(foto.getDados().length));
			return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
		}

		return ResponseEntity.notFound().build();

	}
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Usuario>> buscarPaginado(@PageableDefault(size = 10, sort = "nome") Pageable pagina){						
		Page<Usuario> usuarios = usuarioRepository.findAll(pagina);
		return ResponseEntity.ok(usuarios);
		
	}
	
	@GetMapping("/busca-por-idade")
	public ResponseEntity<Page<Usuario>> buscarPaginado(			
			@RequestParam(defaultValue = "0") int idadeInicial, 
			@RequestParam(defaultValue = "200") int idadeFinal, 
			@PageableDefault(size = 10, sort = "nome") Pageable pagina){
		
		Page<Usuario> usuarios = usuarioRepository
				.findByIdadeBetween(idadeInicial, idadeFinal, pagina);
		
		return ResponseEntity.ok(usuarios);		
	}
	
	@GetMapping("/busca-por-nome")
	public ResponseEntity<Page<Usuario>> buscarPorNomePaginado(			
			@RequestParam(defaultValue = "") String parteDoNome, 			
			@PageableDefault(size = 10, sort = "nome") Pageable pagina){
		
		Page<Usuario> usuarios = usuarioRepository
				.findByNomeContainingIgnoreCase(parteDoNome, pagina);
		
		return ResponseEntity.ok(usuarios);		
	}
}
