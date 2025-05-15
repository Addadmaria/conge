package dz.airalgerie.conge.security;

import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        // Prefix the role label with "ROLE_"
        String roleLabel = user.getRole().getLabel();                // e.g. "admin"
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleLabel);

        // Spring's own UserDetails implementation:
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getMotdepasse(),
            Collections.singleton(authority)
        );
    }
}
