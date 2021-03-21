package pl.coderslab.myapp.calculation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<CalculationComponent> calculationComponentsList;

}
