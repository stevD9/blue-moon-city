package me.stef.bluemooncity.manager;

import me.stef.bluemooncity.MyErrorCode;
import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.exception.MyException;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.model.UserRole;
import me.stef.bluemooncity.op.MyAbstractOperations;
import me.stef.bluemooncity.repo.UserRepository;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import me.stef.bluemooncity.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserManager extends MyAbstractOperations {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User register(UserRegistrationDTO request) {
        User user = mapper.toUser(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.findByEmail(request.getEmail())
                .ifPresentOrElse(u -> {
                        throw new MyException(MyErrorCode.USER_ALREADY_EXISTS);}, () -> {
                            user.setRole(UserRole.USER);
                            if (system.isEmailVerificationEnabled())
                                user.createEmailToken(system.getEmailVerificationMinutes());
                            userRepository.save(user);
                        });

        return user;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new MyException(MyErrorCode.USER_NOT_FOUND));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOne(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new MyException(MyErrorCode.USER_NOT_FOUND));
    }

    public User activateAccount(String otp) {
        User user = userRepository.findById(OtpUtils.userIdFromOtp(otp))
                .orElseThrow(() -> new MyException(MyErrorCode.USER_NOT_FOUND));
        if (!user.getState().getEnabled())
            activateAccount(user, otp);

        return user;
    }

    private void activateAccount(User user, String otp) {
        String token = OtpUtils.tokenFromOtp(otp);

        if (!user.getState().getEmailToken().getId().equals(token))
            throw new MyException(MyErrorCode.INVALID_EMAIL_TOKEN);

        if (user.getState().getEmailToken().getExpiry().before(new Date()))
            throw new MyException(MyErrorCode.EXPIRED_EMAIL_TOKEN);

        user.getState().setEnabled(true);
        user.getState().setEmailToken(null);
    }
}
