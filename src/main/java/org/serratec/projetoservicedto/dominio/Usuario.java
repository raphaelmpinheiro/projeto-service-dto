package org.serratec.projetoservicedto.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

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
	    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	private Foto foto;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Funcao> funcoes = new ArrayList<>();;
				
	public Usuario() {			
	}
		
	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
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

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncao(String funcao) {
		this.funcoes.add(new Funcao(funcao));		
	}
	
	
	//Planilha
	//Nome
	//Rua
	//CEP
}
