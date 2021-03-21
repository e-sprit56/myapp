package pl.coderslab.myapp.calculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.calculation.model.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
