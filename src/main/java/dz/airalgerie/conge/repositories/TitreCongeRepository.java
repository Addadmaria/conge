// src/main/java/dz/airalgerie/conge/repositories/TitreCongeRepository.java
package dz.airalgerie.conge.repositories;

import dz.airalgerie.conge.entities.TitreConge;
import dz.airalgerie.conge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitreCongeRepository extends JpaRepository<TitreConge, Long> {
    List<TitreConge> findByMatricule(User matricule);
}
