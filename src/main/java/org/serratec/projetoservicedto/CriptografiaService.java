package org.serratec.projetoservicedto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {
	
	public String criptografar(String senha) {
		try
		{
			String strkey="faosdfoasjodofja";
			   SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF-8"), "Blowfish");
			        Cipher cipher = Cipher.getInstance("Blowfish");
			        if ( cipher == null || key == null) {
			            throw new RuntimeException("Invalid key or cypher");
			        }
			        cipher.init(Cipher.ENCRYPT_MODE, key);
			String encryptedData =new String(cipher.doFinal(senha.getBytes("UTF-8")));
			return encryptedData;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
}
