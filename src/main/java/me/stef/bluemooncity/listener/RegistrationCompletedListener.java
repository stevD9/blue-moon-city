package me.stef.bluemooncity.listener;

import me.stef.bluemooncity.events.RegistrationCompletedEvent;
import me.stef.bluemooncity.utils.OtpUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class RegistrationCompletedListener extends MailSenderListener<RegistrationCompletedEvent> {

    @EventListener
    @Async
    public void onEvent(RegistrationCompletedEvent event) {
        sendMail(event);
    }

    @Override
    protected String to(RegistrationCompletedEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String subject(RegistrationCompletedEvent event) {
        return  "Registration Confirmation";
    }

    @Override
    protected String message(RegistrationCompletedEvent event) {
        String username = event.getUser().getUsername();
        String otp = OtpUtils.encodeOtp(event.getUser().getId(), event.getUser().getState().getEmailToken().getId());
        String url = event.getAppUrl() + "/registration/complete?token=" + otp;
        String message = """
                Hello %s!
                Please complete registration by clicking here:
                %s
                
                Cheers!
                """;

        return String.format(message, username, url);
    }
}
