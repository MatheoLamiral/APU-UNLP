package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.MailHandler;

import java.util.logging.*;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailHandler extends StreamHandler{

	@Override
	public void publish(LogRecord record) {
		// TODO Auto-generated method stub
		try {
//			String from = "example@logger.com";
//			String to = "destination@mail.com";
			String from = record.getSourceClassName();
			String to = "destination@mail.com";

			// credenciales
			String username = "d1f7bf06d72107"; // Completar con su username de mailtrap
			String password = "28219ddb629774"; // Completar con su password de mailtrap
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.mailtrap.io");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
				new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		            	return new PasswordAuthentication(username, password);
		            }
			});

		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from, "Java logging mail"));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		    message.setSubject(record.getLevel().toString());
		    message.setText(record.getMessage());
		    Transport.send(message);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
	    }
		flush();
	}

}
