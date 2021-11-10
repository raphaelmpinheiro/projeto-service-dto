package org.serratec.projetoservicedto.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Foto;
import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.repositorio.FotoRepository;
import org.serratec.projetoservicedto.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
    @Autowired
	private UsuarioRepository usuarioRepository;

	public Foto inserir(Usuario usuario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		foto.setUsuario(usuario);
		return fotoRepository.save(foto);
	}
	
	public Foto buscar(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			Foto foto = usuario.get().getFoto();
			return foto;			
		}		
		return null;
		
//		Optional<Foto> foto = fotoRepository.findById(id);
//		if(!foto.isPresent()) {
//			return null;
//		}
//		return foto.get();
	}

}
