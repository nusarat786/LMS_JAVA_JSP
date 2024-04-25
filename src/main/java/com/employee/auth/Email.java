package com.employee.auth;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  

public class Email {
	
	public static boolean sendemail(String to ,int OTP) {
		boolean flag = false;
	    String host = "smtp.gmail.com";
	    final String user = "selfielectronic@gmail.com"; // your email address
	    final String password = "oeuz vwnf nvrr csdj"; // your password

	    int port = 587; // Gmail SMTP port

	    // Get the session object
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
	    props.put("mail.smtp.port", port);

	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    // Compose the message
	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject("OTP For LMS");
	        message.setText("OTP IS: " + OTP );

	        // Send the message
	        Transport.send(message);

	        System.out.println("Message sent successfully...");
	        flag =true;
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }finally {
	    	return flag;
	    }
	    
	    
	}
	
	
	public static boolean sendEmail(String[] to, String startDate, String endDate, String applyDate) throws MessagingException {
	    boolean flag = false;
	    String host = "smtp.gmail.com";
	    final String user = "selfielectronic@gmail.com"; // your email address
	    final String password = "oeuz vwnf nvrr csdj"; // your password
	    int port = 587; // Gmail SMTP port

	    // Get the session object
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
	    props.put("mail.smtp.port", port);

	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    	// Compose the message
	
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        
	        // Add recipients
	        for (String recipient : to) {
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	        }
	        
	        message.setSubject("Leave Application Received");
	        message.setText("Dear User,\n\nYour leave application has been successfully received.\n\n"
	                + "Start Date: " + startDate + "\n"
	                + "End Date: " + endDate + "\n"
	                + "Application Date: " + applyDate + "\n\n"
	                + "Thank you.\n"
	                + "Best Regards,\n"
	                );

	        // Send the message
	        Transport.send(message);

	        System.out.println("Leave application message sent successfully...");
	        flag = true;
	     
	        return flag;
	    
	}
	
	public static boolean sendEmail(String[] to, String startDate, String endDate, String applyDate,String mess,int ID,String sub) throws MessagingException {
	    boolean flag = false;
	    String host = "smtp.gmail.com";
	    final String user = "selfielectronic@gmail.com"; // your email address
	    final String password = "oeuz vwnf nvrr csdj"; // your password
	    int port = 587; // Gmail SMTP port

	    // Get the session object
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
	    props.put("mail.smtp.port", port);

	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    // Compose the message
	
		    MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        
	        // Add recipients
	        for (String recipient : to) {
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	        }
	        
	        message.setSubject(sub);
	        message.setText("Dear User,\n\nYour leave application has been: " + mess +" \n\n"
	                + "Start Date: " + startDate + "\n"
	                + "End Date: " + endDate + "\n"
	                + "Proceesed Date: " + applyDate + "\n\n"
	                + "Thank you.\n"
	                + "Best Regards,\n"
	                + "Application ID: " + ID);

	        // Send the message
	        Transport.send(message);

	        System.out.println("Leave application message sent successfully...");
	        flag = true;
	     
	        return flag;
	    
	}
	
	
	public static boolean sendEmail(String[] to,int lvb) throws MessagingException {
	    boolean flag = false;
	    String host = "smtp.gmail.com";
	    final String user = "selfielectronic@gmail.com"; // your email address
	    final String password = "oeuz vwnf nvrr csdj"; // your password
	    int port = 587; // Gmail SMTP port

	    // Get the session object
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
	    props.put("mail.smtp.port", port);

	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    // Compose the message
	
		    MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        
	        // Add recipients
	        for (String recipient : to) {
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	        }
	        
	        message.setSubject("Leave Balance Reset : ");
	        message.setText("Dear Employee,\n\n Your leave Balanace Has Been Reset" +" \n\n"              
	                + "Your Leave Balance is : " + lvb);

	        // Send the message
	        Transport.send(message);

	        System.out.println("Leave application message sent successfully...");
	        flag = true;
	     
	        return flag;
	    
	}

	public static void main(String[] args) {  
		  
	
		  
	}
}
