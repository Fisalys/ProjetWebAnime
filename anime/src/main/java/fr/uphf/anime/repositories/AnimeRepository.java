package fr.uphf.anime.repositories;

import fr.uphf.anime.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {

}
