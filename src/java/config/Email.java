/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author catmi
 */
public class Email {

    private static final String from = "minhpche153232@fpt.edu.vn";
    private static final String password = "nclo vran edqf vcdu";

    public static boolean sendEmail(String to, String code) {
        // Properties: khai bao cac thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };

        // Phien lam viec
        Session session = Session.getInstance(props, auth);

        // Gui email
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //tieu de
            msg.setSubject("Confirm Account");
            //QUy dinh ngay gui
            msg.setSentDate(new Date());
            //Noi dung
            msg.setText("Code for reset your password:\n" + code, "UTF-8");
            // Gui email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendEmailToLinkAccount(String to, String code) {
        // Properties: khai bao cac thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };

        // Phien lam viec
        Session session = Session.getInstance(props, auth);

        // Gui email
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //tieu de
            msg.setSubject("Confirm Account");
            //QUy dinh ngay gui
            msg.setSentDate(new Date());
            //Noi dung
            msg.setText("Code for confirm your account:\n" + code, "UTF-8");
            // Gui email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendEmailToConfirmPay(String to, String code) {
        // Properties: khai bao cac thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };

        // Phien lam viec
        Session session = Session.getInstance(props, auth);

        // Gui email
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //tieu de
            msg.setSubject("Confirm Account");
            //QUy dinh ngay gui
            msg.setSentDate(new Date());
            //Noi dung
            msg.setText("Your OTP Code:\n" + code, "UTF-8");
            // Gui email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public static void main(String[] args) {
        Validate v = new Validate();
        Email.sendEmail("catminh2k1@gmail.com", v.RandomCode());
    }
}
