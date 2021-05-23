package coursework.ecomarket.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private int price;
    @Column(name="discription")
    private String discription;
    @Column(name = "category")
    private String category;
    @Column(name="photo")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Carts> cart;

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDiscription() {
        return discription;
    }
    public Set<Carts> getCart() {
        return cart;
    }
    public String getCategory() {
        return category;
    }
    public int getId() {
        return id;
    }
    public String getPhoto() {
        return photo;
    }
}