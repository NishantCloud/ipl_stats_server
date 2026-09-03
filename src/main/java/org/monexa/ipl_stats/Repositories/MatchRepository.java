package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer> {

    List<Match> findBySeasonIdOrderByMatchDateDesc(Long seasonId);
    List<Match> findAllByOrderByMatchDateDesc();

}
