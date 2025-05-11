package dz.airalgerie.conge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.Dtos.DmdCongeDTO;
import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.services.DmdCongeService;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/dmdconge")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class DmdCongeController {

    @Autowired
    private DmdCongeService dmdCongeService;
    
    @Autowired
    private UserRepository employeRepository;
    @Autowired
    private DmdCongeRepository dmdCongeRepository;
    @Autowired
    private TypeDeCongeRepository typeDeCongeRepository;
   
    @PostMapping("/create")
    public ResponseEntity<?> createConge(@RequestBody  DmdCongeDTO dto) {
        
        User employe = employeRepository.findById(dto.getMatricule())
            .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getMatricule()));

        TypeDeConge typeConge = typeDeCongeRepository.findById(dto.getType_conge())
                .orElseThrow(() -> new RuntimeException("TypeConge not found"));


        DmdConge demande = new DmdConge(
                dto.getDatededamande(),
                dto.getDuree(),
                employe,
                typeConge,
                dto.getStatus()
            );


        dmdCongeRepository.save(demande);

        return ResponseEntity.ok("Demande de congé créée avec succès !");
    }

    @GetMapping("/all")
    public ResponseEntity<List<DmdCongeDTO>> getAllDmdConges() {
        List<DmdCongeDTO> dtos = dmdCongeService.getAllDemandes().stream()
            .map(d -> new DmdCongeDTO(
                d.getDateDeDemande(),                    // date de la demande
                d.getDuree() //matricule
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

   
}