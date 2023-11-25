package me.stef.bluemooncity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class MailSenderListener<T extends ApplicationEvent> implements ApplicationListener<T> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(T event) {
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
