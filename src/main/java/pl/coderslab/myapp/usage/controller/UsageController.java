package pl.coderslab.myapp.usage.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.myapp.property.service.PropertyServiceImpl;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.model.UsageElement;
import pl.coderslab.myapp.usage.service.UsageService;
import pl.coderslab.myapp.usage.usageDTO.UsageDTO;

import javax.validation.Valid;

@Controller
@Data
@AllArgsConstructor
@RequestMapping("/app/usage")
public class UsageController {

	private final UsageService usageService;
	private final RateSchemaService rateSchemaService;
	private final PropertyServiceImpl propertyService;

	@PostMapping("/add-usage")
	public String addUsage(@RequestParam long propertyId, Model model){

		RateSchema rateSchema = rateSchemaService.getRateSchemaByPropertyIdAndActive(propertyId, true);

		if(rateSchema==null){
			return "usage/no-rate-schema";
		}

		List<RateComponent.Type> componentTypes = rateSchemaService.getComponentsTypes(rateSchema);
		List<UsageElement> usageElementList = usageService.getUsageElementList(componentTypes);

		UsageSchema usageSchema = new UsageSchema();
		usageSchema.setRateSchema(rateSchema);
		usageSchema.setProperty(rateSchema.getProperty());
		usageSchema.setUsageElementList(usageElementList);

		model.addAttribute("usageSchema",usageSchema);
		model.addAttribute("month", UsageService.months());
		model.addAttribute("year", UsageService.years());

		return "usage/add-usage";

	}
	@PostMapping("/add-usage-save")
	public String addUsage(@Valid UsageSchema usage, BindingResult result, Model model){

		if (result.hasErrors()){
			model.addAttribute("month", UsageService.months());
			model.addAttribute("year", UsageService.years());
			return "usage/add-usage";
		}

		usage.setMonthControlNumber(usageService.getAbsolutMonthNumber(usage.getMonth(),usage.getYear()));

		usageService.addUsage(usage);

		return "dashboard";
	}

	@GetMapping("/all-property-usage/{propertyId}")
	public String getAllPropertyUsage(@PathVariable("propertyId") long propertyId, Model model){

		List<UsageSchema> allUsageSchema = usageService.getAllUsageSchema(propertyId);
		List<UsageDTO> usageDTOList = usageService.getUsageDtoList(allUsageSchema);

		model.addAttribute(usageDTOList);


		return "/usage/usage-list";

	}

}
