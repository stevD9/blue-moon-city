package me.stef.bluemooncity.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import me.stef.bluemooncity.service.model.LoginDTO;
import org.springframework.stereotype.Component;

@Component
@Path("/sessions")
public class SessionService {

    @POST
    @Path("/")
    public void login(LoginDTO request) {

    }
}
