package me.stef.bluemooncity.config;

import me.stef.bluemooncity.listener.RegistrationActivatedListener;
import me.stef.bluemooncity.listener.RegistrationCompletedListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "system", name = "emailVerificationEnabled", havingValue = "true")
    public static class EmailVerificationConfig {

        @Bean
        public RegistrationCompletedListener registrationCompletedListener() {
            return new RegistrationCompletedListener();
        }

        @Bean
        public RegistrationActivatedListener registrationActivatedListener() {
            return new RegistrationActivatedListener();
        }
    }
}
