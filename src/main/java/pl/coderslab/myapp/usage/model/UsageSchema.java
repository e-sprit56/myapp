package pl.coderslab.myapp.usage.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import pl.coderslab.myapp.property.model.Property;
import pl.coderslab.myapp.rateSchema.model.RateSchema;

@Entity
@Data

public class UsageSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<UsageElement> usageElementList;

	@ManyToOne
	private Property property;

	@ManyToOne
	private RateSchema rateSchema;

	private LocalDate created_at;

	private int month;

	private int year;

	private int monthControlNumber;

	@PrePersist
	protected void onCreate() {
		created_at = LocalDate.now();
	}

}
