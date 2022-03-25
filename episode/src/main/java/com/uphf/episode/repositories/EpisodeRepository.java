package com.uphf.episode.repositories;

import com.uphf.episode.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode,Integer> {

}
