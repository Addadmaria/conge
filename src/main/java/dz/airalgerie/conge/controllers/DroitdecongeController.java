package dz.airalgerie.conge.controllers;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.Droitdeconge;
import dz.airalgerie.conge.Dtos.DroitdecongeDTO;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.entities.Exercice;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.DroitDeCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;
import dz.airalgerie.conge.repositories.ExerciceRepository;
import dz.airalgerie.conge.services.DroitDeCongeService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/droitdeconge")

public class DroitdecongeController {

	@Autowired
    private DroitDeCongeService droitdecongeService;
	
	@Autowired
    private DroitDeCongeRepository droitDeCongeRepository;
	
	@Autowired
    private UserRepository employeRepository;
	
	@Autowired
    private ExerciceRepository exerciceRepository;
    
	@PostMapping("/create")
	   public ResponseEntity<?> createConge(@RequestBody  DroitdecongeDTO dto) {
	       
	       User employe = employeRepository.findById(dto.getMatricule())
	           .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getMatricule()));

	       Exercice idexercice = exerciceRepository.findById(dto.getIdExercice())
	               .orElseThrow(() -> new RuntimeException("Exercice not found"));


	       Droitdeconge droit = new Droitdeconge(
	               dto.getNbrJourConsommes(),
	               dto.getNbrJoursRestants(),
	               employe,
	               idexercice         
	               );


	       // Save it
	       droitDeCongeRepository.save(droit);

	       return ResponseEntity.ok("Droit de congé créée avec succès !");
	   }
    
    @GetMapping("/all")
    public List<Droitdeconge> getAllDroitdeconges() {
       return droitdecongeService.getAllDroits();
    }
}