package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.DmdConge;



@Repository
public interface DmdCongeRepository extends JpaRepository< DmdConge , Integer > {
	
}
