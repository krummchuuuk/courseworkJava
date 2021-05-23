package coursework.ecomarket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

class Pair
{
    private final int ID_product;
    private final int amount;

    public Pair(int ID, int num)
    {
        ID_product   = ID;
        amount = num;
    }

    public int id()   { return ID_product; }
    public int amount() { return amount; }
}

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="number")
    private int number;
    @Column(name="position")
    private Pair position;
    @Column(name="cost")
    private int cost;
}
