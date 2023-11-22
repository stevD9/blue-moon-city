package me.stef.bluemooncity.manager;

import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProfileManager {

    @Autowired
    private UserRepository userRepository;

    public User get(String username) {
        return userRepository.findByUsername(username);
    }
}
