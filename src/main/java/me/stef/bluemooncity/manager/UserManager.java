package me.stef.bluemooncity.manager;

import jakarta.transaction.Transactional;
import me.stef.bluemooncity.MyErrorCode;
import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.exception.MyException;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.model.UserRole;
import me.stef.bluemooncity.repo.UserRepository;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User register(UserRegistrationDTO request) {
        User user = mapper.toUser(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.findByEmail(request.getEmail())
                .ifPresentOrElse(u -> {
                        throw new MyException(MyErrorCode.USER_ALREADY_EXISTS);}, () -> {
                            user.setRole(UserRole.USER);
                            userRepository.save(user);
                        });

        return user;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(MyErrorCode.USER_NOT_FOUND));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
