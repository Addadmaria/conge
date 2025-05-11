package dz.airalgerie.conge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.User;

@Repository
public interface UserRepository extends JpaRepository< User , Integer > {

    Optional<User> findByEmail(String email);

    Optional<User> findByMatricule(Integer valueOf);	
}
