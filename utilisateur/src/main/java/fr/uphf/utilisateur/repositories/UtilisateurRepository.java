package fr.uphf.utilisateur.repositories;

import fr.uphf.utilisateur.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Utilisateur findUtilisateurByUsername(String username);
}
