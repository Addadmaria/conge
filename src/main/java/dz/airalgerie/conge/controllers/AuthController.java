package dz.airalgerie.conge.controllers;

import dz.airalgerie.conge.Dtos.AuthRequest;
import dz.airalgerie.conge.Dtos.AuthResponse;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.UserRepository;
import dz.airalgerie.conge.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // 1) authenticate
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(), 
                    request.getPassword()
                )
            );

            // 2) generate JWT
            String token = jwtUtil.generateToken(request.getEmail());

            // 3) fetch user details
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuthResponse resp = new AuthResponse(
                user.getMatricule(),
                user.getName(),
                user.getRole().getLabel(),
                token
            );
            return ResponseEntity.ok(resp);

        } catch (BadCredentialsException ex) {
            // return plain-text body on 401
            return ResponseEntity
                     .status(HttpStatus.UNAUTHORIZED)
                     .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                     .body("Invalid credentials");
        }
    }
}
