package pl.coderslab.myapp.calculation.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.myapp.calculation.model.Calculation;
import pl.coderslab.myapp.calculation.model.CalculationComponent;
import pl.coderslab.myapp.calculation.repository.CalculationRepository;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.service.RateSchemaService;
import pl.coderslab.myapp.usage.model.UsageSchema;
import pl.coderslab.myapp.usage.service.UsageService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Calculation createCalculation(UsageSchema currentUsageSchema, UsageSchema previousUsageSchema){
        Calculation calculation = new Calculation();
        List<CalculationComponent> calculationComponentList = new ArrayList<>();

        Map<RateComponent.Type, Double> currentUsageMap = usageService.usageSchemaElementsToMap(currentUsageSchema);
        Map<RateComponent.Type, Double> previousUsageMap = usageService.usageSchemaElementsToMap(previousUsageSchema);

        RateSchema rateSchema = currentUsageSchema.getRateSchema();
        Map<RateComponent.Type, Map<String, BigDecimal>> rateSchemaMap = rateSchemaService.getRateSchemaMap(rateSchema);

        rateSchemaMap.entrySet().forEach(entry->{
            CalculationComponent component = new CalculationComponent();
            RateComponent.Type type = entry.getKey();

            component.setType(type);
            component.setFixedCost(entry.getValue().get("fixed"));
            calculationComponentList.add(component);
        });

        calculationComponentList.forEach(component->{
            RateComponent.Type type= component.getType();
            if(currentUsageMap.get(type)!= null && previousUsageMap.get(type)!=null) {
                double typeCalculation = currentUsageMap.get(type) - previousUsageMap.get(type);
                BigDecimal typeCalculationBigDecimal = new BigDecimal(typeCalculation);
                BigDecimal variableCost = rateSchemaMap.get(type).get("variable").multiply(typeCalculationBigDecimal);
                component.setVariableCost(variableCost);
            }
            if (component.getVariableCost() != null) {
                component.setTotalCost(component.getFixedCost().add(component.getVariableCost()));
            } else {
                component.setTotalCost(component.getFixedCost());
            }

        });

//        currentUsageMap.entrySet().forEach(entry ->{
//            CalculationComponent calculationComponent = new CalculationComponent();
//
//            RateComponent.Type type = entry.getKey();
//            calculationComponent.setType(type);
//            calculationComponent.setFixedCost(rateSchemaMap.get(type).get("fixed"));
//
//            double typeCalculation = currentUsageMap.get(type) - previousUsageMap.get(type);
//            BigDecimal typeCalculationBigDecimal = new BigDecimal(typeCalculation);
//            BigDecimal variableCost = rateSchemaMap.get(type).get("variable").multiply(typeCalculationBigDecimal);
//            calculationComponent.setVariableCost(variableCost);
//            calculationComponent.setTotalCost(calculationComponent.getFixedCost().add(calculationComponent.getVariableCost()));
//
//            calculationComponentList.add(calculationComponent);
//        });
//
//        calculation.setCalculationComponentsList(calculationComponentList);
        calculation.setCalculationComponentsList(calculationComponentList);
        return calculation;
    }
}
