package pl.coderslab.myapp.rateSchema.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import pl.coderslab.myapp.property.model.Property;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class RateSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Property property;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<RateComponent> componentList;

}
