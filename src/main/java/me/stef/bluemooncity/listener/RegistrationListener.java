package me.stef.bluemooncity.listener;

import me.stef.bluemooncity.events.RegistrationCompleteEvent;
import me.stef.bluemooncity.utils.OtpUtils;

public class RegistrationListener extends MailSenderListener<RegistrationCompleteEvent> {

    @Override
    protected String to(RegistrationCompleteEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String subject(RegistrationCompleteEvent event) {
        return  "Registration Confirmation";
    }

    @Override
    protected String message(RegistrationCompleteEvent event) {
        String username = event.getUser().getUsername();
        String otp = OtpUtils.encodeOtp(event.getUser().getId(), event.getUser().getState().getEmailToken().getId());
        String url = "/registration/complete?token=" + otp;
        String message = """
                Hello %s!
                Please complete registration by clicking here:
                %s
                
                Cheers!
                """;

        return String.format(message, username, url);
    }
}
