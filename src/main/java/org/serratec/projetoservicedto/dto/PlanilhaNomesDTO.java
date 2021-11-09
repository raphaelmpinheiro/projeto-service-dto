package org.serratec.projetoservicedto.dto;

import org.serratec.projetoservicedto.dominio.Endereco;
import org.serratec.projetoservicedto.dominio.Usuario;

public class PlanilhaNomesDTO {

	private String nome;
	private String email;
	private String rua;
	private String cep;
	
	public Usuario convert() {
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setSenha("123456");
		Endereco endereco = new Endereco();
		endereco.setCep(this.cep);
		usuario.setEndereco(endereco);
		return usuario;
	}
}
