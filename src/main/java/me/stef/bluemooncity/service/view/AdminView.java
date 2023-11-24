package me.stef.bluemooncity.service.view;

import me.stef.bluemooncity.manager.UserManager;
import me.stef.bluemooncity.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminView {

    MyMapper mapper = MyMapper.INSTANCE;

    @Autowired
    private UserManager userManager;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", mapper.toUserDtos(userManager.getAllUsers()));
        return "adminUsers";
    }
}
