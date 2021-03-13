package pl.coderslab.myapp.rateSchema.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateComponentContainer;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("app/rateschema")
@AllArgsConstructor
public class RateSchemaController {

    private final RateSchemaService rateSchemaService;

    @PostMapping("/addschema-components")
    public String addRateSchemaComponents(@RequestParam long propertyId, Model model){
//        przekazać dalej Propertyid
        List<String> components = rateSchemaService.getRateComponentsTypes();

        model.addAttribute("components", components);

        return "rates/choose-components";
    }

    @PostMapping("/add-rates-for-components")
    public String addRatesForComponents(@RequestParam String[] components, Model model){

        RateComponentContainer rateComponentContainer = new RateComponentContainer();
        rateComponentContainer.setComponentList(rateSchemaService.getRateComponentList(components));
        model.addAttribute("rateComponentContainer", rateComponentContainer);

        return "/rates/add-rates-for-components";
    }

    @PostMapping("generate-rate-schema")
    public String generateRateSchema(@Valid RateComponentContainer rateComponentContainer, BindingResult result){

        if(result.hasErrors()){
            return "/rates/add-rates-for-components";
        }

        System.out.println(rateComponentContainer);

        return "/app";
    }






}
