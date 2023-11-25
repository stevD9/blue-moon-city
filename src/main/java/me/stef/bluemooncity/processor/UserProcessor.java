package me.stef.bluemooncity.processor;

import jakarta.transaction.Transactional;
import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.events.RegistrationActivatedEvent;
import me.stef.bluemooncity.events.RegistrationCompletedEvent;
import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.op.MyAbstractOperations;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import me.stef.bluemooncity.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor extends MyAbstractOperations {

    @Autowired
    private UserManager userManager;

    @Transactional
    public User register(UserRegistrationDTO request) {
        User user = userManager.register(request);
        if (system.isEmailVerificationEnabled())
            eventPublisher.publishEvent(new RegistrationCompletedEvent(user, system.getAppUrl(), system.getLocale()));

        return user;
    }

    @Transactional
    public void activateAccount(String otp) {
        User user = userManager.activateAccount(otp);
        eventPublisher.publishEvent(new RegistrationActivatedEvent(user, system.getAppUrl(), system.getLocale()));
    }

    @Transactional
    public void reSendMailOtp(String otp) {
        User user = userManager.getOne(OtpUtils.userIdFromOtp(otp));
        user.createEmailToken(system.getEmailVerificationMinutes());

        eventPublisher.publishEvent(new RegistrationCompletedEvent(user, system.getAppUrl(), system.getLocale()));
    }
}
