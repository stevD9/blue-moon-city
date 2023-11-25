package me.stef.bluemooncity.processor;

import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.events.RegistrationCompleteEvent;
import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.op.MyAbstractOperations;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor extends MyAbstractOperations {

    @Autowired
    private UserManager userManager;

    public User register(UserRegistrationDTO request) {
        User user = userManager.register(request);
        if (system.isEmailVerificationEnabled())
            eventPublisher.publishEvent(new RegistrationCompleteEvent(user, system.getAppUrl(), system.getLocale()));

        return user;
    }
}
