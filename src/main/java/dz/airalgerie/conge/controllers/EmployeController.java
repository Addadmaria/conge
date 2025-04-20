package dz.airalgerie.conge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.Dtos.DmdCongeDTO;
import dz.airalgerie.conge.Dtos.EmployeDTO;
import dz.airalgerie.conge.entities.Affectation;
import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.Employe;
import dz.airalgerie.conge.entities.Fonction;
import dz.airalgerie.conge.entities.Role;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.AffectationRepository;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.EmployeRepository;
import dz.airalgerie.conge.repositories.FonctionRepository;
import dz.airalgerie.conge.repositories.RoleRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.services.DmdCongeService;
import dz.airalgerie.conge.services.EmployeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    @Autowired
    private EmployeService employeService;
    
    @Autowired
    private EmployeRepository employeRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private AffectationRepository affectationRepository;
   
    @PostMapping("/create")
    public ResponseEntity<?> createEmploye(@RequestBody EmployeDTO dto) {
        Role        role        = roleRepository.findById(dto.getRoleId())  
                                 .orElseThrow(() -> new RuntimeException("Role not found"));
        Fonction    fonction    = fonctionRepository.findById(dto.getIdFonction())
                                 .orElseThrow(() -> new RuntimeException("Fonction not found"));
        Affectation affectation = affectationRepository.findById(dto.getIdAffectation())
                                 .orElseThrow(() -> new RuntimeException("Affectation not found"));

        Employe employe = new Employe(
            dto.getDateEntree(),
            dto.getName(),
            dto.getLastname(),     // ← swap these two
            dto.getEmail(),
            dto.getMotdepasse(),
            role,
            affectation,
            fonction
        );

        employeRepository.save(employe);
        return ResponseEntity.ok("Compte créé avec succès !");
    }

//    @GetMapping("/all")
//    public List<DmdConge> getAllDmdConges() {
//    	return EmployeService.getAllDemandes();
//    }
   
}