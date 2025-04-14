package dz.airalgerie.conge.services;

import dz.airalgerie.conge.entities.Exercice;
import dz.airalgerie.conge.repositories.ExerciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {

    @Autowired
    private ExerciceRepository exerciceRepository;

   

    // Récupérer tous les exercices
    public List<Exercice> getAllExercices() {
        return exerciceRepository.findAll();
    }
}