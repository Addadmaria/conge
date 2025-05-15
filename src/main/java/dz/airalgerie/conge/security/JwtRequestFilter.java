package dz.airalgerie.conge.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(CustomUserDetailsService uds, JwtUtil util) {
        this.userDetailsService = uds;
        this.jwtUtil            = util;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
        throws ServletException, IOException {

        String jwtToken = null;

        // 1) Try Authorization header first
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        }

        // 2) If not in header, look for our cookie:
        if (jwtToken == null && request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("JWT_TOKEN".equals(c.getName())) {
                    jwtToken = c.getValue();
                    break;
                }
            }
        }

        if (jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String username = jwtUtil.extractUsername(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                        );
                    auth.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ExpiredJwtException e) {
                // let it fall through as unauthenticated
            }
        }

        chain.doFilter(request, response);
    }
}
