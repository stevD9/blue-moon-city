package me.stef.bluemooncity.security;

import me.stef.bluemooncity.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserPrincipal implements UserDetails {

    private static final String ROLE = "ROLE_";

    private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> ROLE + user.getRole().name());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // TODO ??
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getState().getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // TODO ??
    }

    @Override
    public boolean isEnabled() {
        return user.getState().getEnabled();
    }
}
