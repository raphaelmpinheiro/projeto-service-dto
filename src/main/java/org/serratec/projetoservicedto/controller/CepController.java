package org.serratec.projetoservicedto.controller;

import org.serratec.projetoservicedto.dto.EnderecoDTO;
import org.serratec.projetoservicedto.service.ConsultaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class CepController {

	@Autowired
	ConsultaCepService consultaCep;

	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> get(@PathVariable String cep) {
		try {
			ResponseEntity<EnderecoDTO> endereco = consultaCep.getEndereco(cep);
			if (endereco.getStatusCode() == HttpStatus.OK) {
				return endereco;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
}
