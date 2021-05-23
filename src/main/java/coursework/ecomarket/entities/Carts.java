package coursework.ecomarket.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="carts")
public class Carts {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToMany(mappedBy="cart")
    private Set<Products> products;
    @Column(name="cost")
    private int cost;

    public Carts(int id) {
        this.id = id;
    }
    public Carts() {}
}