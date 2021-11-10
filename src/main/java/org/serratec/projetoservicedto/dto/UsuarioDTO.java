package org.serratec.projetoservicedto.dto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UsuarioDTO {
	private long id;
	private String nome;
	private String email;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UsuarioDTO() {	
	}
	
	public UsuarioDTO(long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	//No DTO criamos um construtor e fazemos um De-Para do objeto
	// de domínio para o objeto DTO.
	// O Objeto DTO só tem as informações que são pertinentes a visualização do Front-end.
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.url = generateUrlFoto(usuario);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public static List<UsuarioDTO> convert(List<Usuario> usuarios){
		List<UsuarioDTO> usuariosDto = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			UsuarioDTO usuarioDto = new UsuarioDTO(usuario);			
			usuariosDto.add(usuarioDto);			
		}
		return usuariosDto;
	}	
	
	public static String generateUrlFoto(Usuario usuario) {
		return UsuarioDTO.generatedUrlFoto(usuario.getId());	
	}
	
	public static String generatedUrlFoto(long idUsuario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/api/usuario/{id}/foto")
				.buildAndExpand(idUsuario)
				.toUri();
		
		return uri.toString();	
	}
}
