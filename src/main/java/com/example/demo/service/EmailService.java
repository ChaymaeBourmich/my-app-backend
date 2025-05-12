package com.example.demo.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.mail.MessagingException;


import java.io.IOException;
import java.util.List;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(String to, String subject, String body, List<MultipartFile> attachments) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true pour multipart

        helper.setTo(to);
        helper.setSubject(subject);
        // Assurez-vous que le corps est bien défini
        helper.setText(body, true); // true pour HTML, false pour texte brut

        // Pièces jointes
        if (attachments != null) {
            for (MultipartFile file : attachments) {
                helper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
            }
        }

        // Envoyer l'email
        mailSender.send(message);
    }

}
