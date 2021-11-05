package org.serratec.projetoservicedto.repositorio;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);

}
