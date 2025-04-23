package dz.airalgerie.conge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.Dtos.ExerciceDTO;
import dz.airalgerie.conge.entities.Exercice;
import dz.airalgerie.conge.repositories.ExerciceRepository;

import java.util.List;

@RestController
@RequestMapping("/api/exercices")
public class ExerciceController {

    @Autowired
    private ExerciceRepository exerciceRepository;

    // Créer un exercice
    @PostMapping("/create")
    public ResponseEntity<?> createExercice(@RequestBody ExerciceDTO dto) {
        Exercice exercice = new Exercice();
        exercice.setLabel(dto.getLabel()) ; // 👈 LocalDate (format "yyyy-MM-dd")

        exerciceRepository.save(exercice);
        
        return ResponseEntity.ok("Exercice créé avec succès !");
    }

    // Lister tous les exercices
    @GetMapping("/all")
    public List<Exercice> getAllExercices() {
        return exerciceRepository.findAll();
    }
}