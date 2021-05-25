package coursework.ecomarket.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "PostTag")
@Table(name = "cart_products")
public class CartProduct{
    @EmbeddedId
    private CartProductId id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cart_id")
    private Carts cart;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("product_id")
    private Products product;
 
    @Column(name = "counter")
    private int counter;
 
    private CartProduct() {}
 
    public CartProduct(Carts cart, Products product) {
        this.cart = cart;
        this.product = product;
        this.id = new CartProductId(cart.getId(), product.getId());
    }
    public Products getProduct() {
        return product;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CartProduct that = (CartProduct) o;
        return Objects.equals(cart, that.cart) && Objects.equals(product, that.product);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(cart, product);
    }
}