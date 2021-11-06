package org.serratec.projetoservicedto.dto;

import org.serratec.projetoservicedto.dominio.Usuario;

public class UsuarioInserirDTO {
	private String nome;
	private String email;
	private String senha;
		
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario createUsuario() {
		return new Usuario(this.nome, this.email, this.senha);
	}
	
	

}
