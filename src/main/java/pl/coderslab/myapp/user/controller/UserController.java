package pl.coderslab.myapp.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.myapp.user.model.User;
import pl.coderslab.myapp.security.CurrentUser;
import pl.coderslab.myapp.user.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String creatUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registration";
    }

    @PostMapping("/registration")
    public String creatUser(@Valid User user, BindingResult result){

        if(result.hasErrors()){
            return "user/registration";
        }
        userService.saveUser(user);

        return "redirect:/app";

    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }
}
