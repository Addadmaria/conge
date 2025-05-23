package dz.airalgerie.conge.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter  = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // completely ignore /css/**, /js/**, /images/**, etc.
        return (web) -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(authz -> authz
              .requestMatchers(HttpMethod.GET, "/login", "/css/**", "/js/**").permitAll()
              .requestMatchers(HttpMethod.POST, "/login").permitAll()
                  .requestMatchers("/error").permitAll()
              .requestMatchers("/api/auth/**").permitAll()
              .requestMatchers("/api/employes/create").permitAll()
              .requestMatchers("/demandes/en-cours").hasRole("sous_directeur")
              .requestMatchers("/demandes/approuver").hasRole("responsable_ah")
              .requestMatchers("/titres").hasRole("admin")
              .anyRequest().authenticated()
          )
          // disable the built‐in formLogin
          .formLogin(form -> form.disable())
          // stateless sessions
          .sessionManagement(sm -> 
              sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          // our auth provider + JWT filter
          .authenticationProvider(authenticationProvider())
          .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

          .exceptionHandling(ex -> ex
            .accessDeniedPage("/errorAcces")
          );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
