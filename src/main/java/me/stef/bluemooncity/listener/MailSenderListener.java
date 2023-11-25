package me.stef.bluemooncity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class MailSenderListener<T> {

    @Autowired
    private JavaMailSender mailSender;

    protected void sendMail(T event) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to(event));
        mail.setSubject(subject(event));
        mail.setText(message(event));

        mailSender.send(mail);
    }

    protected abstract String to(T t);
    protected abstract String subject(T t);
    protected abstract String message(T t);
}
