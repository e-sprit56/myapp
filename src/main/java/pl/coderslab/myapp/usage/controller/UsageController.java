package pl.coderslab.myapp.usage.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.myapp.property.service.PropertyServiceImpl;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.model.UsageElement;
import pl.coderslab.myapp.usage.service.UsageService;

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

		UsageSchema usageSchema = new UsageSchema();
		usageSchema.setRateSchema(rateSchema);
		usageSchema.setProperty(rateSchema.getProperty());
		List<RateComponent.Type> componentTypes = rateSchemaService.getComponentsTypes(rateSchema);
//		List<UsageElement> usageElementList = new ArrayList<>();
//
//		componentTypes.forEach(type -> {
//			UsageElement usageComponent = new UsageElement();
//			usageComponent.setType(type);
//			usageComponent.setSymbol(UsageService.typesAndSymbolsMap.get(type));
//			usageElementList.add(usageComponent);
//		});

		List<UsageElement> usageElementList = usageService.getUsageElementList(componentTypes);
		usageSchema.setUsageElementList(usageElementList);

		model.addAttribute("usageSchema",usageSchema);
		model.addAttribute("months", UsageService.months());
		model.addAttribute("years", UsageService.years());

		return "usage/add-usage";

	}
	@PostMapping("/add-usage-save")
	public String addUsage(@Valid UsageSchema usage, BindingResult result){

		if (result.hasErrors()){
			return "usage/add-usage";
		}

		usageService.addUsage(usage);

		return "dashboard";
	}

}
