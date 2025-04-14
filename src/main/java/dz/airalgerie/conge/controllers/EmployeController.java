package dz.airalgerie.conge.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.airalgerie.conge.entities.Employe;
import dz.airalgerie.conge.services.EmployeService;

@RestController
@RequestMapping(path = "/api/employe")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @PostMapping("/create")
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }
    
    @GetMapping("/employes")
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();    
    }
    
    
}








