package me.stef.bluemooncity.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.service.model.UserDTO;
import me.stef.bluemooncity.service.model.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/users")
public class UserService {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private UserManager userManager;

    @POST
    @Path("/")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public UserDTO register(
            UserRegistrationDTO request) {
        return mapper.toUserDTO(userManager.register(request));
    }
}
