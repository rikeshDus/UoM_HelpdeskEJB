package businessLogic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Session Bean implementation class SendMailSSL
 */
@Stateless
@LocalBean
public class SendMailSSL {

    /**
     * Default constructor. 
     */
    public SendMailSSL() {
        // TODO Auto-generated constructor stub
    }
    
    
    public String sendMail(final String from,final String password,String to,String subject,String content) {
    	String mgs="fail";
    	
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,password);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(content);
 
			Transport.send(message);
 
			mgs="Done";
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		props.clear();
		return mgs;
	}//end of public String sendMail(String from,String to) {
    

}
