package coursework.ecomarket.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToMany
    @JoinTable(
            name="orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    public Set<Products> prod;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client cli;

    public Order(Set<Products> prod, Client cli) {
        this.prod = prod;
        this.cli = cli;
    }
    public Order() {}

    public Set<Products> getProd() {
        return prod;
    }
    public Client getCli() {
        return cli;
    }
    public int getId() {
        return id;
    }
    public void setCartProd(Set<Products> prod) {
        this.prod = prod;
    }
    public void setCli(Client cli) {
        this.cli = cli;
    }
}
