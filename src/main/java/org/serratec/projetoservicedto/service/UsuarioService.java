package org.serratec.projetoservicedto.service;

import java.util.List;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.exception.EmailException;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcritpBCryptPasswordEncoder;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario inserir(Usuario user) throws EmailException {
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if(usuario != null) {
			throw new EmailException("Usuário com este e-mail já existe no cadastro.");			
		}
		
		user.setSenha(bcritpBCryptPasswordEncoder.encode(user.getSenha()));
		return usuarioRepository.save(user);
	}
}
