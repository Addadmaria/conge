package dz.airalgerie.conge.controllers;

import dz.airalgerie.conge.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public LoginController(AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil     = jwtUtil;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value="error", required=false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Email ou mot de passe incorrect");
        }
        return "login";  // renders templates/login.html
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpServletResponse response,
                          Model model) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException ex) {
            model.addAttribute("loginError", "Email ou mot de passe incorrect");
            return "login";  // back to login with error
        }

        // Generate JWT
        String token = jwtUtil.generateToken(email);

        // Store it in an HttpOnly cookie
        ResponseCookie cookie = ResponseCookie.from("JWT_TOKEN", token)
                                              .httpOnly(true)
                                              .path("/")
                                              .maxAge(3600)
                                              .build();
        response.addHeader("Set-Cookie", cookie.toString());

        // **Redirect on success** to /acceuil
        return "redirect:/acceuil";
    }
}
