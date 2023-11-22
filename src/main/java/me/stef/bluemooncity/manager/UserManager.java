package me.stef.bluemooncity.manager;

import jakarta.transaction.Transactional;
import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.model.UserRole;
import me.stef.bluemooncity.repo.UserRepository;
import me.stef.bluemooncity.service.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User register(UserRegistrationDTO request) {
        User user = mapper.toUser(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);

        return user;
    }
}
