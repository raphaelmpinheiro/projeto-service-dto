package org.serratec.projetoservicedto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {
	
	public String criptografar(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
}