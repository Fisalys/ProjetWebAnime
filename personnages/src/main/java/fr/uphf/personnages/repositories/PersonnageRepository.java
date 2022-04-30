package fr.uphf.personnages.repositories;

import fr.uphf.personnages.models.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PersonnageRepository extends JpaRepository<Personnage,Integer> {
    List<Personnage> findPersonnagesByIdAnime(Integer idAnime);

}
