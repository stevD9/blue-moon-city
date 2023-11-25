package me.stef.bluemooncity.service.rest;

import jakarta.validation.Valid;
import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.processor.UserProcessor;
import me.stef.bluemooncity.service.rest.model.UserDTO;
import me.stef.bluemooncity.service.rest.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class UserService {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private UserProcessor userProcessor;

    @PostMapping("/users")
    public UserDTO register(
            @RequestBody @Valid UserRegistrationDTO request) {
        return mapper.toUserDTO(userProcessor.register(request));
    }
}
