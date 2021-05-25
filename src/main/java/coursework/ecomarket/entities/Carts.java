package coursework.ecomarket.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cart_products", joinColumns = @JoinColumn(name="cart_id"), inverseJoinColumns = @JoinColumn(name="products_id"))
    private Set<Products> products;
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
    public Set<Products> getProducts() {
        return products;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}