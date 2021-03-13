package pl.coderslab.myapp.rateSchema.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.myapp.property.service.PropertyServiceImpl;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("app/rateschema")
@AllArgsConstructor
public class RateSchemaController {

    private final RateSchemaService rateSchemaService;
    private final PropertyServiceImpl propertyService;

    @PostMapping("/addschema-components")
    public String addRateSchemaComponents(@RequestParam long propertyId, Model model, RedirectAttributes redirectAttributes) {

        List<String> components = rateSchemaService.getRateComponentsTypes();

        model.addAttribute("components", components);
        model.addAttribute("propertyId", propertyId);

        return "rates/choose-components";
    }

    @PostMapping("/add-rates-for-components")
    public String addRatesForComponents(@RequestParam String[] components, Model model, @RequestParam long propertyId) {


        RateSchema rateSchema = new RateSchema();
        rateSchema.setComponentList(rateSchemaService.getRateComponentList(components));
        rateSchema.setProperty(propertyService.getProperty(propertyId));

        model.addAttribute("rateSchema", rateSchema);

        return "/rates/add-rates-for-components";
    }

    @PostMapping("generate-rate-schema")
    public String generateRateSchema(@Valid RateSchema rateSchema, BindingResult result) {

        if (result.hasErrors()) {
            return "/rates/add-rates-for-components";
        }

        rateSchemaService.addRateSchema(rateSchema);


        return "/dashboard";
    }


}
