package org.serratec.projetoservicedto.repositorio;

import java.util.Optional;

import org.serratec.projetoservicedto.dominio.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long>{	

}
