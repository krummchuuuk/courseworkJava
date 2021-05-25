package coursework.ecomarket.repositories;

import org.springframework.data.repository.CrudRepository;

import coursework.ecomarket.entities.UserRole;

public interface RoleRepo extends CrudRepository<UserRole, Long>{
    
}
