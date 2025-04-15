package dz.airalgerie.conge.controllers;

import dz.airalgerie.conge.entities.Exercice;
import dz.airalgerie.conge.services.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercices")
public class ExerciceController {

    @Autowired
    private ExerciceService exerciceService;

    
}