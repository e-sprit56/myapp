package pl.coderslab.myapp.rateSchema.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.myapp.rateSchema.model.RateComponent;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.rateSchema.repository.RateSchemaRepository;


import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
@Data
public class RateSchemaService {

    private final RateSchemaRepository rateSchemaRepository;



    public List<String> getRateComponentsTypes(){
        List<String> typesList = new ArrayList<>();
        typesList.add("Wynajem");
        typesList.add("Opłaty Administracyjne");
        typesList.add("Woda");
        typesList.add("Gaz");
        typesList.add("Prąd");
        typesList.add("TV");
        typesList.add("Internet");

        return typesList;
    }

    public static Map<String, RateComponent.Type> ComponentStringTypeMap;

    static {
        ComponentStringTypeMap = new HashMap<>();
        ComponentStringTypeMap.put("Wynajem", RateComponent.Type.RENT);
        ComponentStringTypeMap.put("Opłaty Administracyjne", RateComponent.Type.ADMINISTRATION);
        ComponentStringTypeMap.put("Woda", RateComponent.Type.WATER);
        ComponentStringTypeMap.put("Gaz", RateComponent.Type.GAS);
        ComponentStringTypeMap.put("Prąd", RateComponent.Type.ELECTRICITY);
        ComponentStringTypeMap.put("TV", RateComponent.Type.TV);
        ComponentStringTypeMap.put("Internet", RateComponent.Type.BROADBAND);
    }

    public List<RateComponent> getRateComponentList(String[] components){
        List<String> componentsList = Arrays.asList(components);
        List<RateComponent> rateComponents = new ArrayList<>();
        componentsList.forEach(component->{
            RateComponent rateComponent = new RateComponent();
            rateComponent.setType(ComponentStringTypeMap.get(component));
            rateComponent.setPlDescription(component);
            rateComponents.add(rateComponent);
        });

        return  rateComponents;
    }

    public List<RateComponent.Type> getComponentsTypes(RateSchema rateSchema){
        List<RateComponent> rateComponents = rateSchema.getComponentList();
        List<RateComponent.Type> types = new ArrayList<>();

        rateComponents.forEach(rateComponent -> {
            types.add(rateComponent.getType());
        });

        return types;
    }

    public Map<RateComponent.Type, Map<String, BigDecimal>> getRateSchemaMap(RateSchema rateSchema){

        Map<RateComponent.Type, Map<String, BigDecimal>> rateSchemaMap = new HashMap<>();

        rateSchema.getComponentList().forEach(rateComponent -> {

            Map<String, BigDecimal> rateComponentMap = new HashMap<>();
            rateComponentMap.put("fixed", rateComponent.getFixedRate());
            rateComponentMap.put("variable", rateComponent.getVariableRate());

            rateSchemaMap.put(rateComponent.getType(), rateComponentMap);
        });

        return rateSchemaMap;
    }

    public RateSchema getRateSchemaByPropertyIdAndActive(long propertyId, boolean active){
        return  rateSchemaRepository.getRateSchemaByPropertyIdAndActive(propertyId, active);
    }

    public void saveRateSchema(RateSchema rateSchema){
        rateSchemaRepository.save(rateSchema);
    }

    public RateSchema getRateSchema(long rateSchemaId){
        return rateSchemaRepository.getRateSchemaById(rateSchemaId);
    }


}
