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
import pl.coderslab.myapp.usage.usageDTO.UsageDTO;

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

	public static List<Integer> months() {
		return IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	}

	public static List<Integer> years() {
		return IntStream.rangeClosed(2020,2030).boxed().collect(Collectors.toList());
	}

	public void addUsage(UsageSchema usage){
		usageRepository.save(usage);
	}

	public UsageSchema findUsageSchemaById (long id){
		return usageRepository.findUsageSchemaById(id);
	}

	public UsageSchema findPreviousUsageSchema(int monthControlNumber){
		return usageRepository.findUsageSchemaByMonthControlNumberLessThan(monthControlNumber);
	}

	public List<UsageSchema> getAllUsageSchema(long propertyId){
		return usageRepository.findAllByPropertyId(propertyId);
	}

	public List<UsageDTO> getUsageDtoList(List<UsageSchema> usageSchemaList){

		List<UsageDTO> usageDTOList = new ArrayList<>();

		usageSchemaList.forEach(usageSchema -> {


			UsageDTO usageDTO = new UsageDTO();
			usageDTO.setMonth(usageSchema.getMonth());
			usageDTO.setYear(usageSchema.getYear());

			List<UsageElement> usageElementList = usageSchema.getUsageElementList();

			usageElementList.forEach(usageElement -> {
				if(usageElement.getType() == RateComponent.Type.WATER){
					usageDTO.setWaterUsage(usageElement.getUsage());
					usageDTO.setWaterUsageSymbol(usageElement.getSymbol());
				}
				if(usageElement.getType() == RateComponent.Type.GAS){
					usageDTO.setGasUsage(usageElement.getUsage());
					usageDTO.setGasUsageSymbol(usageElement.getSymbol());
				}
				if(usageElement.getType() == RateComponent.Type.ELECTRICITY){
					usageDTO.setElectricityUsage(usageElement.getUsage());
					usageDTO.setElectricityUsageSymbol(usageElement.getSymbol());
				}

			});

			usageDTOList.add(usageDTO);
		});

		return usageDTOList;
	}

	public int getAbsolutMonthNumber(int month, int year){
		return month+(year-2020)*12;
	}

	public Map<RateComponent.Type, Double> usageSchemaElementsToMap(UsageSchema usageSchema){
		Map<RateComponent.Type, Double> typeAndUsageMap= new HashMap<>();

		usageSchema.getUsageElementList().forEach(usageElement -> {
			typeAndUsageMap.put(usageElement.getType(),usageElement.getUsage());
		});

		return typeAndUsageMap;
	}





}
