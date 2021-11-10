package org.serratec.projetoservicedto.dto;

import org.serratec.projetoservicedto.dominio.Foto;
import org.serratec.projetoservicedto.dominio.Usuario;

public class FotoDTO {
	private String nome;
	private String url;
	
	public FotoDTO(Foto foto, Usuario usuario) {
		this.nome = foto.getNome();
		this.url = UsuarioDTO.generateUrlFoto(usuario);		 
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}
