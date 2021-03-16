package pl.coderslab.myapp.rateSchema.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.myapp.property.model.Property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class RateComponent {

    public enum Type {
        WATER, ELECTRICITY, GAS, RENT, ADMINISTRATION, TV, BROADBAND
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Type type;

    @DecimalMin(value = "0.0", inclusive = true, message = "podaj wartość liczbową w formacie 00.00")
    @Digits(integer=4, fraction=2)
    private BigDecimal fixedRate;

    @DecimalMin(value = "0.0", inclusive = true, message = "podaj wartość liczbową w formacie 00.00")
    @Digits(integer=4, fraction=3)
    private BigDecimal variableRate;

    private String plDescription;



}
