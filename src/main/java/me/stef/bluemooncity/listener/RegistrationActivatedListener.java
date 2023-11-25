package me.stef.bluemooncity.listener;

import me.stef.bluemooncity.events.RegistrationActivatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class RegistrationActivatedListener extends MailSenderListener<RegistrationActivatedEvent> {

    @EventListener
    @Async
    public void onEvent(RegistrationActivatedEvent event) {
        sendMail(event);
    }

    @Override
    protected String to(RegistrationActivatedEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String subject(RegistrationActivatedEvent event) {
        return "Your account is now active";
    }

    @Override
    protected String message(RegistrationActivatedEvent event) {
        String username = event.getUser().getUsername();
        String login = event.getAppUrl() + "/login";
        String profile = event.getAppUrl() + "/profile";
        String message = """
                Hello %s!
                You are in!!
                You can login here:
                %s
                
                And see your profile here:
                %s
                
                Cheers!
                """;

        return String.format(message, username, login, profile);
    }
}
