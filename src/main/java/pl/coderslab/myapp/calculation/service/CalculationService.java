package pl.coderslab.myapp.calculation.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.myapp.calculation.CalculationDto.CalculationDto;
import pl.coderslab.myapp.calculation.model.Calculation;
import pl.coderslab.myapp.calculation.model.CalculationComponent;
import pl.coderslab.myapp.calculation.repository.CalculationRepository;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.service.UsageService;

import java.math.BigDecimal;
import java.util.*;

@Service
@Data
@AllArgsConstructor
public class CalculationService {

    private final CalculationRepository calculationRepository;

    private final UsageService usageService;

    private final RateSchemaService rateSchemaService;

    public void saveCalculation (Calculation calculation){
        calculationRepository.save(calculation);
    }

    public Calculation createCalculation(UsageSchema currentUsageSchema, UsageSchema previousUsageSchema, int months){
        Calculation calculation = new Calculation();
        List<CalculationComponent> calculationComponentList = new ArrayList<>();

        Map<RateComponent.Type, Double> currentUsageMap = usageService.usageSchemaElementsToMap(currentUsageSchema);
        Map<RateComponent.Type, Double> previousUsageMap = usageService.usageSchemaElementsToMap(previousUsageSchema);

        RateSchema rateSchema = currentUsageSchema.getRateSchema();
        Map<RateComponent.Type, Map<String, BigDecimal>> rateSchemaMap = rateSchemaService.getRateSchemaMap(rateSchema);

        rateSchemaMap.entrySet().forEach(entry->{

            CalculationComponent calculationComponent = new CalculationComponent();

            BigDecimal monthsBigDec = new BigDecimal(months);

            RateComponent.Type type = entry.getKey();
            calculationComponent.setType(type);

            BigDecimal fixedCost = Optional.ofNullable(entry.getValue().get("fixed")).orElse(new BigDecimal(0));
            calculationComponent.setFixedCost(fixedCost.multiply(monthsBigDec));

            double currentUsage = Optional.ofNullable(currentUsageMap.get(type)).orElse(0d);
            double previousUsage = Optional.ofNullable(previousUsageMap.get(type)).orElse(0d);

            double typeCalculation = currentUsage - previousUsage;

            BigDecimal typeCalculationBigDecimal = new BigDecimal(typeCalculation);

            BigDecimal variebleCost = Optional.ofNullable(rateSchemaMap.get(type).get("variable")).orElse(new BigDecimal(0));
            BigDecimal variableCost = variebleCost.multiply(typeCalculationBigDecimal);

            calculationComponent.setVariableCost(variableCost);
            calculationComponent.setTotalCost(calculationComponent.getFixedCost().add(calculationComponent.getVariableCost()));

            calculationComponentList.add(calculationComponent);


        });


        calculation.setCalculationComponentsList(calculationComponentList);
        calculation.setPeriod(setPeriod(currentUsageSchema,previousUsageSchema));


        return calculation;
    }

    private String setPeriod(UsageSchema currentUsageSchema, UsageSchema previousUsageSchema){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(previousUsageSchema.getMonth()).append("/").append(previousUsageSchema.getYear())
                .append(" - ").append(currentUsageSchema.getMonth()).append("/").append(currentUsageSchema.getYear());

        return stringBuilder.toString();
    }

    private Map<RateComponent.Type, CalculationComponent> getCalculationComponentsMap(Calculation calculation){

        Map<RateComponent.Type, CalculationComponent> calculationComponentMap = new HashMap<>();

        calculation.getCalculationComponentsList().forEach(component -> {
            calculationComponentMap.put(component.getType(), component);
        });
        return calculationComponentMap;

    }

    public CalculationDto creatCalculationDto(Calculation calculation){
        CalculationDto calculationDto = new CalculationDto();

        Map<RateComponent.Type, CalculationComponent> calculationComponentMap = getCalculationComponentsMap(calculation);

        calculationDto.setPeriod(calculation.getPeriod());
        calculationDto.setRent(calculationComponentMap.get(RateComponent.Type.RENT));
        calculationDto.setAdministration(calculationComponentMap.get(RateComponent.Type.ADMINISTRATION));
        calculationDto.setWater(calculationComponentMap.get(RateComponent.Type.WATER));
        calculationDto.setGas(calculationComponentMap.get(RateComponent.Type.GAS));
        calculationDto.setElectricity(calculationComponentMap.get(RateComponent.Type.ELECTRICITY));
        calculationDto.setTv(calculationComponentMap.get(RateComponent.Type.TV));
        calculationDto.setBroadband(calculationComponentMap.get(RateComponent.Type.BROADBAND));

        return calculationDto;

    }


}
