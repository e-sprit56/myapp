package pl.coderslab.myapp;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.myapp.user.model.User;
import pl.coderslab.myapp.security.CurrentUser;



@Controller
public class HomeController {

    @GetMapping("/app")
    public String home(){

        return "dashboard";
    }



    @GetMapping("/check")
    @ResponseBody
    public String checkUser(@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();

        return "name: " + entityUser.getUsername() + " id :" +entityUser.getId();
    }



    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}
