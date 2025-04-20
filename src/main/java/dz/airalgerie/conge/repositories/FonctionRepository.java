package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import dz.airalgerie.conge.entities.Fonction;

@Repository
public interface FonctionRepository extends JpaRepository< Fonction , Long > {
	
}
