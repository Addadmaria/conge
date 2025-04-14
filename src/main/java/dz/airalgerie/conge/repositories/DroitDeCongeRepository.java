package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dz.airalgerie.conge.entities.Droitdeconge;





@Repository
public interface DroitDeCongeRepository extends JpaRepository< Droitdeconge , Integer > {
	
}
