package cis.springboot.Users;

import cis.springboot.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails admin=userRepository.getByUsername(username);
        if(admin==null)
        {
            throw  new NotFoundException("There isn't a user with this username !!");
        }
        else
            return admin;
    }

    public List<Admin> getAllAdmins(){
        return userRepository.findAll();
    }
    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder().encode(admin.getPassword()));
        return userRepository.save(admin);
    }
}
