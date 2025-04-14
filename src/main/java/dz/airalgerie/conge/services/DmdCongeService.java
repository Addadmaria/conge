package dz.airalgerie.conge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.repositories.DmdCongeRepository;

@Service
public class DmdCongeService {

    @Autowired
    private DmdCongeRepository DmdCongeRepository;

    public DmdConge addDmdConge(DmdConge dmdconge) {
        return DmdCongeRepository.save(dmdconge);
    }
    
    
    public List<DmdConge> getAllDemandes() {
        return DmdCongeRepository.findAll();
    }
}

