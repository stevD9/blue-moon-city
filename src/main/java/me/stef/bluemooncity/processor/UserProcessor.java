package me.stef.bluemooncity.processor;

import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.events.registration.RegistrationCompleteEvent;
import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserProcessor {

    @Value("${app.url}")
    private String appUrl;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public User register(UserRegistrationDTO request) {
        User user = userManager.register(request);

        RegistrationCompleteEvent event = new RegistrationCompleteEvent(user, appUrl, Locale.ENGLISH);

        return user;
    }
}
