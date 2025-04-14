package dz.airalgerie.conge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dz.airalgerie.conge.repositories.DroitDeCongeRepository;
import dz.airalgerie.conge.entities.Droitdeconge;

@Service
public class DroitDeCongeService {

    @Autowired
    private DroitDeCongeRepository droitdeCongeRepository;

    public Droitdeconge addDroiDeConge(Droitdeconge droitdeconge) {
        return droitdeCongeRepository.save(droitdeconge);
    }
    
    
    
   

    
    
}

