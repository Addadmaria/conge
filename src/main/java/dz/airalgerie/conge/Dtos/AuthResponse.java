package dz.airalgerie.conge.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Integer matricule;
    private String name;
    private String role;
    private String token;
}
