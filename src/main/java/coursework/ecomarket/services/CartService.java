package coursework.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coursework.ecomarket.entities.Carts;
import coursework.ecomarket.repositories.CartRepo;

@Service
public class CartService {
    @Autowired
    CartRepo cRepo;

    public void update(Carts cart) {
        cRepo.save(cart);
    }
}
