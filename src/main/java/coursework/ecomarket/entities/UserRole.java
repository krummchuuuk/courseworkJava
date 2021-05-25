package coursework.ecomarket.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class UserRole implements GrantedAuthority{
    @Id 
    private int id;
    private String name;
    @ManyToMany(mappedBy="roles")
    private Set<Client> clients;
    
    public UserRole(String roleName) {
        this.name=roleName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }
}
