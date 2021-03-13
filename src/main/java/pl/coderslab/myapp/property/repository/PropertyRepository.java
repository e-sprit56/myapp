package pl.coderslab.myapp.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.property.model.Property;
import pl.coderslab.myapp.security.CurrentUser;
import pl.coderslab.myapp.user.model.User;

import java.net.UnknownServiceException;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    public List<Property> getPropertiesByOwner(User user);

    public Property getPropertyById(long propertyId);
}
