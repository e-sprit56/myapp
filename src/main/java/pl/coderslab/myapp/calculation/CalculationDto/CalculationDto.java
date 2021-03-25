package pl.coderslab.myapp.calculation.CalculationDto;

import lombok.Data;
import pl.coderslab.myapp.calculation.model.CalculationComponent;

import java.math.BigDecimal;

@Data
public class CalculationDto {

    private String period;

    private CalculationComponent rent;

    private CalculationComponent administration;

    private CalculationComponent water;

    private CalculationComponent gas;

    private CalculationComponent electricity;

    private CalculationComponent tv;

    private CalculationComponent broadband;
}
