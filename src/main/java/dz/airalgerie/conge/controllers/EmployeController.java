package dz.airalgerie.conge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.Dtos.DmdCongeDTO;
import dz.airalgerie.conge.Dtos.UserDTO;
import dz.airalgerie.conge.entities.Affectation;
import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.entities.Fonction;
import dz.airalgerie.conge.entities.Role;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.AffectationRepository;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;
import dz.airalgerie.conge.repositories.FonctionRepository;
import dz.airalgerie.conge.repositories.RoleRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.services.DmdCongeService;
import dz.airalgerie.conge.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/employes")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", allowCredentials = "true")
public class EmployeController {

    
    @Autowired
    private UserRepository employeRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
   
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
        Role role = roleRepository.findById(dto.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        Fonction fonction = fonctionRepository.findById(dto.getIdFonction())
            .orElseThrow(() -> new RuntimeException("Fonction not found"));
        Affectation aff = affectationRepository.findById(dto.getIdAffectation())
            .orElseThrow(() -> new RuntimeException("Affectation not found"));

        User e = User.builder()
            .name(dto.getName())
            .lastname(dto.getLastname())
            .dateEntree(dto.getDateEntree())
            .email(dto.getEmail())
            .motdepasse(passwordEncoder.encode(dto.getMotdepasse()))
            .role(role)
            .fonctionEntity(fonction)
            .affectationEntity(aff)
            .build();

        employeRepository.save(e);
        return ResponseEntity.ok("Compte créé avec succès !");
    }


//    @GetMapping("/all")
//    public List<DmdConge> getAllDmdConges() {
//    	return UserService.getAllDemandes();
//    }
   
}