package fr.uphf.personnages.repositories;

import fr.uphf.personnages.models.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<Personnage,Integer> {

}
