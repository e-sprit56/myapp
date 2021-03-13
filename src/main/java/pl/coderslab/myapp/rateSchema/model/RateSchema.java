package pl.coderslab.myapp.rateSchema.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.myapp.property.model.Property;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private Set<RateComponent> rateComponents;


}
