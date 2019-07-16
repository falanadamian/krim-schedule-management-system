package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String recipient, String subject, String content, boolean multipart){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setFrom("damianmswsis@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch(Exception e){
            LOGGER.warn("Wysyłanie maila zakończone niepowodzeniem: {}", e.getMessage());
        }
    }

    @Async
    public void sendActivationEmail(User user){
        sendEmail("falanadamian@gmail.com", "Hello", "Contento", true);
    }

    @Async
    public void sendAdminActivationEmail(User user){
        sendEmail("falanadamian@gmail.com",
                "Otwarte zgłoszenie rejestracyjne",
                "Nowe zgłoszenie rejestracyjne od użytkownika: " + user.getFirstName() + " " + user.getLastName() +
                        ". Kliknij link, aby aktywować konto: <a href='http://localhost:4200/authentication/user-activation/" + user.getActivationKey() +  "'> Aktywuj konto </a>",
                true);
    }

    public void sendUserActivatedEmail(User user) {
        sendEmail("falanadamian@gmail.com",
                "Konto aktywowane",
                "Twoje konto zostało aktywowane! Nazwa użytkownika: " + user.getUsername() +
                        ". <a href='http://localhost:4200'>Przejdź do strony</a>" ,
                true);
    }

    public void sendResetPasswordEmail(User user) {
        sendEmail(user.getEmail(),
                "Przywracanie hasła",
                "Na ten adres email zlecone zostało żądanie zresetowania hasła. Kliknij aby dokończyć proces: " +
                        "<a href='http://localhost:4200/authentication/reset-password/" + user.getResetKey() + "'>Ustaw hasło</a>" ,
                true);
    }

    public void sendResetPasswordCompletedEmail(User user) {
        sendEmail(user.getEmail(),
                "Hasło zmienione",
                "Proces zmiany hasła zakończony pomyślnie." ,
                true);
    }

    public void sendAccountCreatedEmail(User user) {
        sendEmail(user.getEmail(),
                "Witaj w systemie zarządzania rozkładem zajęć KRiM",
                "Witaj " + user.getFirstName() + ", Twoje konto zostało właśnie utworzone. Aby dokończyć proces, odwiedź następujący link:  " +
                        "<a href='http://localhost:4200/authentication/reset-password/" + user.getResetKey() + "'>Kontynuuj</a>" ,
                true);
    }

}
