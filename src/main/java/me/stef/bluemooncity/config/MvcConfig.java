package me.stef.bluemooncity.config;

import me.stef.bluemooncity.properties.MySystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MySystem system;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index.html");
        registry.addViewController("/registration").setViewName("/registration.html");
        String registrationComplete = system.isEmailVerificationEnabled() ? "/registrationSuccess.html" : "registrationActive.html";
        registry.addViewController("/registration/success").setViewName(registrationComplete);
        registry.addViewController("/test-auth").setViewName("/testAuth.html");
        registry.addViewController("/admin").setViewName("/testAdmin.html");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    // beans

//    @Bean
//    public LocaleResolver localeResolver() {
//        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return cookieLocaleResolver;
//    }
//
//    @Bean
//    public EmailValidator usernameValidator() {
//        return new EmailValidator();
//    }
//
//    @Bean
//    public PasswordMatchesValidator passwordMatchesValidator() {
//        return new PasswordMatchesValidator();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(RequestContextListener.class)
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }
//
//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource);
//        return validator;
//    }
//
    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return factory -> factory.setRegisterDefaultServlet(true);
    }
}
