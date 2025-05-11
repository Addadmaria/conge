package dz.airalgerie.conge.security;

import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getLabel());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getMotdepasse(),
                Collections.singletonList(authority)
        );
    }
}