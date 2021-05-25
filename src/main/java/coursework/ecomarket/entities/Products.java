package coursework.ecomarket.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> carts;

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDiscription() {
        return discription;
    }
    public List<CartProduct> getCart() {
        return carts;
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
    public void addCart(Carts cart, Products product) {
        carts.add(new CartProduct(cart, product));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products product = (Products) o;
        return Objects.equals(id, product.getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}