package dz.airalgerie.conge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.DmdCongeDTO;
import dz.airalgerie.conge.entities.Employe;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.EmployeRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.services.DmdCongeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/dmdconge")
@RequiredArgsConstructor
public class DmdCongeController {

    @Autowired
    private DmdCongeService dmdCongeService;
    
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private DmdCongeRepository dmdCongeRepository;
    @Autowired
    private TypeDeCongeRepository typeDeCongeRepository;
   
    @PostMapping("/create")
    public ResponseEntity<?> createConge(@RequestBody  DmdCongeDTO dto) {
        
        Employe employe = employeRepository.findById(dto.getMatricule())
            .orElseThrow(() -> new RuntimeException("Employe not found with id " + dto.getMatricule()));

        TypeDeConge typeConge = typeDeCongeRepository.findById(dto.getType_conge())
                .orElseThrow(() -> new RuntimeException("TypeConge not found"));


        DmdConge demande = new DmdConge(
                dto.getDatededamande(),
                dto.getDuree(),
                employe,
                typeConge
            );
        // Build the entity



        // Save it
        dmdCongeRepository.save(demande);

        return ResponseEntity.ok("Demande de congé créée avec succès !");
    }

    @GetMapping("/all")
    public List<DmdConge> getAllDmdConges() {
    	return dmdCongeService.getAllDemandes();
    }
   
}