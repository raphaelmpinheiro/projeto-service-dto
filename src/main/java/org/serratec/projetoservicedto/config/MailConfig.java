package org.serratec.projetoservicedto.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class MailConfig {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	public void sendMail(String para, String assunto, String conteudo) {
		
		//Enviando email com anexo
		MimeMessage message = javaMailSender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("raphael.pinheiro.serratec@gmail.com");
	        helper.setTo(para);
	        helper.setSubject(assunto);
	        //Envio do texto plano
	        //helper.setText("Dados da inscrição:\n" + conteudo + "\n\n\n Serratec residencia Petrópolis.");
	        
	        //Envio formato Html
	        helper.setText("<h2>" + conteudo + "</h2>" + "<h3>Envio em <b>Html</b></h3>", true);
	        	        	      
	        helper.addAttachment("MyTestFile.txt", new ByteArrayResource("teste".getBytes()));
	        javaMailSender.send(message);
	    } catch (MessagingException e) {

	        e.printStackTrace();
	    }
		
	    
	    //Enviando email simples
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("raphael.pinheiro.serratec@gmail.com");
		message.setTo(para);
		message.setSubject(assunto);		
		message.setText("Dados da inscrição:\n" + conteudo + "\n\n\n Serratec residencia Petrópolis." );
		message
		javaMailSender.send(message);//SMTP			
		*/
	}
}
