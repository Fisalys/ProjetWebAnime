package com.uphf.episode.repositories;

import com.uphf.episode.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode,Integer> {


    List<Episode> findEpisodesByIdAnime(Integer idAnime);
}
