package dz.airalgerie.conge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.User;



@Repository
public interface DmdCongeRepository extends JpaRepository< DmdConge , Integer > {
	List<DmdConge> findByStatus(String status);
    @Query("SELECT d FROM DmdConge d JOIN FETCH d.matricule JOIN FETCH d.typeConge WHERE d.status = 'en cours'")
    List<DmdConge> findAllEnCoursWithRelations();

    List<DmdConge> findByMatricule(User matricule);
}
