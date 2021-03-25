package pl.coderslab.myapp.usage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.property.model.Property;
import pl.coderslab.myapp.rateSchema.model.RateSchema;
import pl.coderslab.myapp.usage.model.UsageSchema;

import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<UsageSchema, Long> {

    List<UsageSchema> findAllByPropertyId(long propertyId);

    UsageSchema findUsageSchemaById(long usageSchemaId);

    UsageSchema findFirstUsageSchemaByPropertyAndMonthControlNumberLessThanOrderByMonthControlNumberDesc(Property property, int monthControlNumber);
}
