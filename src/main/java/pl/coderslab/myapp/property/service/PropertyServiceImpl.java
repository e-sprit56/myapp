package pl.coderslab.myapp.property.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import pl.coderslab.myapp.property.model.Property;
import pl.coderslab.myapp.property.repository.PropertyRepository;
import pl.coderslab.myapp.security.CurrentUser;
import pl.coderslab.myapp.user.model.User;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl {

    private final PropertyRepository propertyRepository;

    public void addProperty(Property property, CurrentUser currentUser){

        User user = currentUser.getUser();
        property.setOwner(user);

        propertyRepository.save(property);
    }

    public List<Property> getPropertyList(User user){


        return propertyRepository.getPropertiesByOwner(user);
    }
}
