package me.stef.bluemooncity.config;

import me.stef.bluemooncity.listener.RegistrationListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    @ConditionalOnProperty(prefix = "system", name = "emailVerificationEnabled", havingValue = "true")
    public RegistrationListener registrationListener() {
        return new RegistrationListener();
    }
}
