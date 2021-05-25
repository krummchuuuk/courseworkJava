package coursework.ecomarket.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coursework.ecomarket.entities.Client;
import coursework.ecomarket.repositories.ClientRepo;
import coursework.ecomarket.repositories.RoleRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ClientRepo cRepo;
    @Autowired
    RoleRepo rRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client cli = cRepo.findByUsername(username);
        if (cli == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return cli;
    }
    public UserDetails findUserById(int id) {
        return cRepo.findById(id);
    }

    public boolean saveClient(Client cli) {
        if (cRepo.findByUsername(cli.getUsername())==null) {
            cli.setPassword(bCryptPasswordEncoder.encode(cli.getPassword()));
            cRepo.save(cli);
            return true;
        }
        else return false;
    }
}
