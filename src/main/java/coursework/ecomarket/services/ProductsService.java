package coursework.ecomarket.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coursework.ecomarket.entities.Products;
import coursework.ecomarket.repositories.ProductsRepo;

interface productService {
    public List<Products> showByCategory(String category);
    public Products findById(int id);
}

@Service
public class ProductsService implements productService {
    @Autowired
    ProductsRepo productRepo;

    @Transactional
    public List<Products> showByCategory(String category) {
        if (category == null) {
            return (List<Products>) productRepo.findAll();
        }
        else {
            return productRepo.findByCategory(category);
        }
    }
    @Transactional
    public Products findById(int id) {
        return productRepo.findById(id);
    }
}
