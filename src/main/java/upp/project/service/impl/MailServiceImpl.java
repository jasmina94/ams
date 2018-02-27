package upp.project.service.impl;

import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import upp.project.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Override
	public void send(String executionId, String processInstanceId, String emailTo) {
		System.out.println("Usao u slanje maila " + executionId + " " + processInstanceId);
		final String username = "zijdev@gmail.com";
		final String password = "mojalozinka12";
		    
		SecureRandom random = new SecureRandom();
		String hashCode = new BigInteger(130, random).toString(32);
		String activationLink = "http://localhost:8080/api/registration/active/" + processInstanceId + "/" + executionId;
		
		String m_to = emailTo, m_subject = "AMS Account",
				m_text = "Hi,\t\n\t\nThank you for registering on our website.\t\n"
						+ "To activate your account please go on link: " + activationLink +
						"\t\n\t\nBest Regards,\t\nYour Restaurant.";
		Properties props = new Properties();
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");		
		props.put("mail.smtp.socketFactory.port", "465");

		Session session = Session.getInstance(props,
		        new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		            }
		        });

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setSubject(m_subject);
			msg.setText(m_text);
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", Integer.valueOf("465"), "AMS", password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

			System.out.println("Poslao mail");
		} catch (AddressException e) {
			e.printStackTrace();
			return;
		} catch (MessagingException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Izlazi iz maila");
		
	}

}
