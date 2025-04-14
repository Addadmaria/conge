package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import dz.airalgerie.conge.entities.Exercice;



@Repository
public interface ExerciceRepository extends JpaRepository< Exercice , Integer > {
	
}
