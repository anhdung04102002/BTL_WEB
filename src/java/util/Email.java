/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Windows 10 TIMT
 */
public class Email {
    public static void send(String to, String subject, String text){
        final String username_mail = "9385c714896962";
        final String password = "2f2e275c196a34";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");
        Session session_mail = Session.getInstance(props,
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username_mail, password);
           }
        });
        try {
          Message message = new MimeMessage(session_mail);
          message.setHeader("Content-Type", "text/plain; charset=UTF-8");
          message.setFrom(new InternetAddress(username_mail));
          message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to));
          message.setSubject(subject);
          message.setContent(text, "text/html; charset=utf-8");
          Transport.send(message);
        } catch (MessagingException e) {
          throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        
    }
}
