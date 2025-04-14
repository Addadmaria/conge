package dz.airalgerie.conge.controllers;

import dz.airalgerie.conge.entities.Droitdeconge;
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

    @PostMapping("/create")
    public Droitdeconge createDroitdeconge(@RequestBody Droitdeconge droitdeconge) {
        return droitdecongeService.addDroiDeConge(droitdeconge);
    }

}