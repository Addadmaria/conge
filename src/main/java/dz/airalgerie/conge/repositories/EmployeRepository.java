package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.Employe;

@Repository
public interface EmployeRepository extends JpaRepository< Employe , Integer > {
	
}
