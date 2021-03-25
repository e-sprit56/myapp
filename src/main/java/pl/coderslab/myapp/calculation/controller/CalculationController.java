package pl.coderslab.myapp.calculation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.myapp.calculation.model.Calculation;
import pl.coderslab.myapp.calculation.service.CalculationService;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.service.UsageService;

@Controller
@AllArgsConstructor
@RequestMapping("app/calculation")
public class CalculationController {

    private final CalculationService calculationService;

    private final UsageService usageService;

    @GetMapping("/create-calculation/{usageSchemaId}")
    public String addCalculation(@PathVariable("usageSchemaId") long usageSchemaId, Model model) {

        UsageSchema currentUsageSchema = usageService.findUsageSchemaById(usageSchemaId);
        UsageSchema previousUsageSchema = usageService.findPreviousUsageSchema(currentUsageSchema.getMonthControlNumber());

        if (previousUsageSchema == null) {
            return "/calculation/no-calculation";
        }

        int monthsBetweenUsage = currentUsageSchema.getMonthControlNumber() - previousUsageSchema.getMonthControlNumber();

        Calculation calculation = calculationService.createCalculation(currentUsageSchema, previousUsageSchema, monthsBetweenUsage);
        calculationService.saveCalculation(calculation);
        model.addAttribute("calculation", calculationService.creatCalculationDto(calculation));
        return "calculation/calculation-result";

    }

}

