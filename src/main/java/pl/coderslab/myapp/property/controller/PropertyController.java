package pl.coderslab.myapp.property.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.myapp.property.model.Property;
import pl.coderslab.myapp.property.service.PropertyServiceImpl;
import pl.coderslab.myapp.security.CurrentUser;
import pl.coderslab.myapp.user.model.User;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app/properties")
public class PropertyController {

    private final PropertyServiceImpl propertyService;

    @GetMapping("")
    public String properties(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("properties", propertyService.getPropertyList(user));

        return "property/properties";
    }

    @GetMapping("/add-property")
    public String addProperty(Model model){
        Property property = new Property();
        List<Property.Type> typeList = Arrays.asList(Property.Type.values());

        model.addAttribute("property", property);
        model.addAttribute("types", typeList);

        return "property/add-property";
    }

    @PostMapping("/add-property")
    public String addProperty(@Valid Property property, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){

        if(result.hasErrors()){
            return "property/add-property";
        }
        propertyService.addProperty(property, currentUser) ;

        return "redirect:/app/properties";
    }

}
