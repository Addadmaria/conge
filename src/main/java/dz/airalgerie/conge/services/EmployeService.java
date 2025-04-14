package dz.airalgerie.conge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.airalgerie.conge.entities.Employe;
import dz.airalgerie.conge.repositories.EmployeRepository;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }
    
    
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}

