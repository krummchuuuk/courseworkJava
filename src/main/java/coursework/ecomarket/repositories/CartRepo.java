package coursework.ecomarket.repositories;

import org.springframework.data.repository.CrudRepository;

import coursework.ecomarket.entities.Carts;

public interface CartRepo extends CrudRepository<Carts, Long>{
}
