package pl.coderslab.myapp.rateSchema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RateComponentContainer {

    private List<RateComponent> componentList;
}
