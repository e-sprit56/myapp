package pl.coderslab.myapp.rateSchema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.rateSchema.model.RateSchema;

@Repository
public interface RateSchemaRepository extends JpaRepository<RateSchema, Long> {

    RateSchema getRateSchemaByPropertyIdAndActive(long propertyId, boolean isActive);
}
