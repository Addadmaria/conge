package dz.airalgerie.conge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.services.DmdCongeService;

@RestController
@RequestMapping(path = "/api/dmdconge")
public class DmdCongeController {

    @Autowired
    private DmdCongeService dmdCongeService;

   
    @PostMapping("/create")
    public DmdConge createDmdConge(@RequestBody DmdConge dmdConge) {
        return dmdCongeService.addDmdConge(dmdConge);
    }

    @GetMapping("/all")
    public List<DmdConge> getAllDmdConges() {
        return dmdCongeService.getAllDemandes();
    }

 
    

   
}