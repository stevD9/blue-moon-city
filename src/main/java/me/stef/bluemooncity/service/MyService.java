package me.stef.bluemooncity.service;

import jakarta.ws.rs.*;
import me.stef.bluemooncity.manager.MyProfileManager;
import me.stef.bluemooncity.mapper.MyMapper;
import me.stef.bluemooncity.service.model.UpdateMyPasswordDTO;
import me.stef.bluemooncity.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Path("/my-profile")
public class MyService {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private MyProfileManager myProfileManager;

    @GET
    @Path("/")
    public UserDTO getMe(Authentication authentication) {
        return mapper.toUserDTO(myProfileManager.get(((UserDetails) authentication.getPrincipal()).getUsername()));
    }

    @PUT
    @Path("/password")
    public void updatePassword(
            UpdateMyPasswordDTO request) {

    }
}
