package me.stef.bluemooncity.events;

import me.stef.bluemooncity.entity.User;

public abstract class UserEvent {

    private final User user;

    protected UserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
