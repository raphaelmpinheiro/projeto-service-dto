package org.serratec.projetoservicedto.dominio;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id;
	
	private String nome;
	private String email;
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
				
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha) {
		super();		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
		
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getId() {
		return this.id;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return id == other.id;
	}		
	
	
	@Override
	public String toString() {
		return "Usuário: " + this.getEmail() + " - " + "Nome: " + this.getNome();
	}
	
	
	//Planilha
	//Nome
	//Rua
	//CEP
}
