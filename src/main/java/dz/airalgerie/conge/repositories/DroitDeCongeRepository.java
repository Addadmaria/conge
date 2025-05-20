package dz.airalgerie.conge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.Droitdeconge;
import dz.airalgerie.conge.entities.User;

@Repository
public interface DroitDeCongeRepository extends JpaRepository<Droitdeconge, Long> {
    List<Droitdeconge> findByMatricule(User matricule);
}
