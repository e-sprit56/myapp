package pl.coderslab.myapp.usage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.usage.model.UsageSchema;
@Repository
public interface UsageRepository extends JpaRepository<UsageSchema, Long> {


}
