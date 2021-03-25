package pl.coderslab.myapp.property.model;

import lombok.Data;
import pl.coderslab.myapp.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "properties")
@Data
public class Property {

    public enum Type {
        FLAT, GARAGE, HOLIDAY_HOUSE, OFFICE, HOUSE,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String shortName;

    @ManyToOne
    private User owner;

    private String city;
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "format 00-000")
    private String postCode;

    private String streetAndNumber;

    @Enumerated(EnumType.STRING)
    private Type type;
}
