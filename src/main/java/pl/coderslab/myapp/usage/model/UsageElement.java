package pl.coderslab.myapp.usage.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import pl.coderslab.myapp.rateSchema.model.RateComponent;

@Entity
@Data
@NoArgsConstructor
public class UsageElement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private RateComponent.Type type;

	@Digits(integer = 5, fraction = 4, message ="podaj liczbe")
	private Double usage;

	private String symbol;


}
