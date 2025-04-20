package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.Affectation;

@Repository
public interface AffectationRepository extends JpaRepository< Affectation , Long > {
	
}
