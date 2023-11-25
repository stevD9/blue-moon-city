package me.stef.bluemooncity.events;

import me.stef.bluemooncity.entity.User;

import java.util.Locale;

public class RegistrationCompleteEvent extends UserEvent {

    private String appUrl;
    private Locale locale;

    public RegistrationCompleteEvent(User user, String appUrl, Locale locale) {
        super(user);
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
}
