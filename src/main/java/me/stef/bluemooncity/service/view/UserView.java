package me.stef.bluemooncity.service.view;

import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserView {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private UserManager userManager;

    @GetMapping("/profile")
    public String getMyProfile(
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("user", mapper.toUserDTO(userManager.getByUsername(userDetails.getUsername())));
        return "profile";
    }
}