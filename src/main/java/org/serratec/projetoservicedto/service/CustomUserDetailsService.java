package org.serratec.projetoservicedto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Funcao;
import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {						
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if(usuario.isPresent()) {
			
			List<GrantedAuthority>  authorits = new ArrayList<>();
			for (Funcao funcao : usuario.get().getFuncoes()) {
				authorits.add(new SimpleGrantedAuthority(funcao.getFuncao()));				
			}
			
			//AuthorityUtils.createAuthorityList("ROLE_admin", "ROLE_usuario");
			
			return new User(
					usuario.get().getEmail(), 
					usuario.get().getSenha(),
					authorits				
				);
		}
		throw new UsernameNotFoundException("Usuário " + username + " não encontrado.");

	}
}
