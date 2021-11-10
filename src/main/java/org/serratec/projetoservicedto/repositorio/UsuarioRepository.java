package org.serratec.projetoservicedto.repositorio;

import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
	Usuario findByNome(String nome);

}
