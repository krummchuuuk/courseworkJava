package coursework.ecomarket.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="carts")
public class Carts {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="cost")
    private int cost;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartProduct> products;
    @OneToOne(mappedBy = "cart")
    private Client client;

    public Carts() {}
    public Carts(Client cli)  {
        this.client = cli;
        this.cost=0;
    }
    public Client getClient() {
        return client;
    }
    public int getId() {
        return id;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public Set<CartProduct> getProducts() {
        return products;
    }
    public void addProduct(Carts cart, Products product) {
        this.products.add(new CartProduct(cart, product));
    }
    public void setClient(Client client) {
        this.client = client;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carts cart = (Carts) o;
        return Objects.equals(id, cart.getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}