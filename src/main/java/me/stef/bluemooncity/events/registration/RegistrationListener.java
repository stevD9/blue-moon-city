package me.stef.bluemooncity.events.registration;

import me.stef.bluemooncity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();

        String subject = "Registration Confirmation";
        String url = "/registration/complete?token=" + user.getState().getEmailToken().getId();
        String message = "Complete your registration\r\n" + event.getAppUrl() + url;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }
}
