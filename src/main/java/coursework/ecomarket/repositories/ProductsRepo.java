package coursework.ecomarket.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import coursework.ecomarket.entities.Products;

public interface ProductsRepo extends CrudRepository<Products, Long> {
    List<Products> findall();
    List<Products> findByCategory(String category);
    Products findById(int id);
}
