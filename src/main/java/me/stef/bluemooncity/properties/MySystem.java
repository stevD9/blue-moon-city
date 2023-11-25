package me.stef.bluemooncity.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@ConfigurationProperties("system")
public class MySystem {

    private boolean emailVerificationEnabled;
    private Integer emailVerificationMinutes;
    private String appUrl;
    private Locale locale;

    public boolean isEmailVerificationEnabled() {
        return emailVerificationEnabled;
    }

    public void setEmailVerificationEnabled(boolean emailVerificationEnabled) {
        this.emailVerificationEnabled = emailVerificationEnabled;
    }

    public Integer getEmailVerificationMinutes() {
        return emailVerificationMinutes;
    }

    public void setEmailVerificationMinutes(Integer emailVerificationMinutes) {
        this.emailVerificationMinutes = emailVerificationMinutes;
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
