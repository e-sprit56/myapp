package pl.coderslab.myapp.calculation.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<CalculationComponent> calculationComponentsList;

}
