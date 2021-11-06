package org.serratec.projetoservicedto.service;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoservicedto.CriptografiaService;
import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.exception.EmailException;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CriptografiaService criptografiaService;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getById(long id) {
		return usuarioRepository.findById(id);
	}
	
	public Usuario inserir(Usuario user) throws EmailException {
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if(usuario != null) {
			throw new EmailException("Usuário com este e-mail já existe no cadastro.");			
		}		
		user.setSenha(criptografiaService.criptografar(user.getSenha()));
		return usuarioRepository.save(user);
	}
}
