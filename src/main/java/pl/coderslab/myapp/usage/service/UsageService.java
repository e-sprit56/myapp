package pl.coderslab.myapp.usage.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.usage.model.UsageElement;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.repository.UsageRepository;

@Service
@Data
@AllArgsConstructor
public class UsageService {

	private final UsageRepository usageRepository;

	public static Map<RateComponent.Type, String> typesAndSymbolsMap;

	static {
		typesAndSymbolsMap = new HashMap<>();
		typesAndSymbolsMap.put(RateComponent.Type.ADMINISTRATION, null);
		typesAndSymbolsMap.put(RateComponent.Type.RENT, null);
		typesAndSymbolsMap.put(RateComponent.Type.WATER, "m3");
		typesAndSymbolsMap.put(RateComponent.Type.ELECTRICITY, "kwh");
		typesAndSymbolsMap.put(RateComponent.Type.GAS, "m3");
		typesAndSymbolsMap.put(RateComponent.Type.TV, null);
		typesAndSymbolsMap.put(RateComponent.Type.BROADBAND, null);
	}

	public List<UsageElement> getUsageElementList(List<RateComponent.Type> componentTypes){

		List<UsageElement> usageElementList = new ArrayList<>();

		componentTypes.forEach(type -> {

			if(typesAndSymbolsMap.get(type)!=null) {
				UsageElement usageElement = new UsageElement();
				usageElement.setType(type);
				usageElement.setSymbol(typesAndSymbolsMap.get(type));
				usageElementList.add(usageElement);
			}
		});

		return usageElementList;

	}




//	public List<RateComponent> getRateComponentList(String[] components){
//		List<String> componentsList = Arrays.asList(components);
//		List<RateComponent> rateComponents = new ArrayList<>();
//		componentsList.forEach(component->{
//			RateComponent rateComponent = new RateComponent();
//			rateComponent.setType(ComponentStringTypeMap.get(component));
//			rateComponent.setPlDescription(component);
//			rateComponents.add(rateComponent);
//		});

	public static List<Integer> months() {
		return IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	}

	public static List<Integer> years() {
		return IntStream.rangeClosed(2020,2030).boxed().collect(Collectors.toList());
	}

	public void addUsage(UsageSchema usage){
		usageRepository.save(usage);
	}





}
