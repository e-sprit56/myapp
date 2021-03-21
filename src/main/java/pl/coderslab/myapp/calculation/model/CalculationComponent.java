package pl.coderslab.myapp.calculation.model;

import lombok.Data;
import pl.coderslab.myapp.rateSchema.model.RateComponent;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class CalculationComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private RateComponent.Type type;

    private BigDecimal fixedCost;

    private BigDecimal variableCost;

    private BigDecimal totalCost;
}
