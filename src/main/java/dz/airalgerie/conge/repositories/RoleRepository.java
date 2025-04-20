package dz.airalgerie.conge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.airalgerie.conge.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository< Role , Long > {
	
}
