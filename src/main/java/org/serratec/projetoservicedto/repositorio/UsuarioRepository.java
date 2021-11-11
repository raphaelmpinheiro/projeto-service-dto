package org.serratec.projetoservicedto.repositorio;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
	Usuario findByNome(String nome);	
	Usuario findByIdade(int idade);
	
	@Query(value = "SELECT * FROM USUARIO u WHERE u.idade >= :idadeInicial AND u.idade <= :idadeFinal", nativeQuery = true)
	Page<Usuario> buscarUsuariosEntreIdades(int idadeInicial, int idadeFinal, Pageable pageable);
		
	//Configuração por convenção.
	Page<Usuario> findByIdadeBetween(int idadeInicial, int idadeFinal, Pageable pageable);
	
	Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
