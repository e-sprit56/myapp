package pl.coderslab.myapp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.myapp.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
