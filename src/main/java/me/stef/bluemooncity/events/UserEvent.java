package me.stef.bluemooncity.events;

import me.stef.bluemooncity.entity.User;
import org.springframework.context.ApplicationEvent;

public abstract class UserEvent extends ApplicationEvent {

    private final User user;

    protected UserEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
