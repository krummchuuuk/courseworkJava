package coursework.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coursework.ecomarket.entities.Order;
import coursework.ecomarket.repositories.OrderRepo;

@Service
public class OrderService {
    @Autowired
    OrderRepo oRepo;

    public void saveChanges(Order order) {
        oRepo.save(order);
    }
    public Order getOrderById(int id) {
        return oRepo.getById(id);
    }
}
