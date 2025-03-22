package com.hrv.taskmanager.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service class for email related operations
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Method to send an email to the user with the reset password link
     * @param to the email address of the user
     * @param token the token to reset the password
     */
    public void sendForgotPasswordEmail(String to, String token) {
        this.sendEmail(to, "Forgot Password",
                "Click in the link to reset your password: https://miapp.com/reset-password?token="+token);
    }

    /**
     * Method to send an email to the user with the verification link
     * @param to the email address of the user
     * @param subject the subject of the email
     * @param text the text of the email
     *
     */
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
}
