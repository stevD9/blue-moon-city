package me.stef.bluemooncity.events.registration;

import me.stef.bluemooncity.entity.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class RegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public RegistrationCompleteEvent(User user, String appUrl, Locale locale) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
        this.locale = locale;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
