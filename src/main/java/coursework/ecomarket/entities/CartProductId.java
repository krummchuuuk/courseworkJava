package coursework.ecomarket.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductId implements Serializable {
    @Column(name = "cart_id")
    private int cartId;
 
    @Column(name = "product_id")
    private int productId;
 
    private CartProductId() {}
 
    public CartProductId(int cartId, int productId) {
        this.cartId = cartId;
        this.productId = productId;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CartProductId that = (CartProductId) o;
        return Objects.equals(cartId, that.cartId) &&
               Objects.equals(productId, that.productId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }
}
