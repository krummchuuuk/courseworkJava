package coursework.ecomarket.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="usrs")
public class Users {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="Surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;
    @OneToMany(mappedBy="usr")
    private List<Orders>orders;
    @Column(name="cart")
    private Carts cart;

    private Users()  {
        this.cart = new Carts(this.id);
    }
}
