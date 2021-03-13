package pl.coderslab.myapp.property.model;

import lombok.Data;
import pl.coderslab.myapp.user.model.User;

import javax.persistence.*;

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

    private String postCode;

    private String streetAndNumber;

    private Type type;
}
