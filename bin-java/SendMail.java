import java.util.Properties; import javax.mail.*; import javax.mail.internet.*;
public class SendMail {
	public static void Email(String recipient, String text) {
		final String username = "email";
		final String password = "password";
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password); } } );
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("email"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setText(text);
			Transport.send(message); }
		catch (MessagingException e) {throw new RuntimeException(e); } }
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "email", "password");
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			//Address[] in = msg.getFrom();
			//for (Address address : in) {System.out.println("FROM:" + address.toString()); }
			//Multipart mp = (Multipart) msg.getContent();
			//BodyPart bp = mp.getBodyPart(0);
			System.out.println(msg.getSentDate());
			System.out.println("SUBJECT:" + msg.getSubject());
			//System.out.println("CONTENT:" + bp.getContent());
		} catch (Exception mex) {mex.printStackTrace(); } } }
