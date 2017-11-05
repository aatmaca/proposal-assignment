package ayas.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {


    public static void sendMail(String from, String to, String subject, String message) {
        sendMail(from, to, subject, message, true);
    }

    public static void sendMail(String from, String to, String subject, String message, boolean automaticMail) {

        if (to == null) {
            return;
        }
        
        SimpleMailMessage templateMessage = new SimpleMailMessage();
        
        templateMessage.setFrom(from);
        templateMessage.setTo(to);
        templateMessage.setSubject(subject);
        templateMessage.setText(message);

        templateMessage.setCc(Constants.PROJECT_EMAIL_ADDRESS);
//        templateMessage.setReplyTo(Constants.PROJECT_EMAIL_ADDRESS);
//        templateMessage.setBcc(Constants.PROJECT_EMAIL_ADDRESS);


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("asmpt.bilkent.edu.tr");
       // mailSender.send(templateMessage);
//        System.out.println(message);
    }
    
}