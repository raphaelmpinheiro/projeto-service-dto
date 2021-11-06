package org.serratec.projetoservicedto;

import org.serratec.projetoservicedto.exception.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(EmailException.class)	
	protected ResponseEntity<Object> handleEmailExecption(EmailException ex){				
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());		
	}
}
