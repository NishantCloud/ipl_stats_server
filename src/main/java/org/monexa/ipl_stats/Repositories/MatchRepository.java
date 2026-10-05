package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.Matches;
import org.monexa.ipl_stats.entity.TeamMatchStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Matches,Integer> {

    @Query("""
        SELECT t
        FROM matches t
        WHERE t.seasons.seasonId = :seasonId
            Order by t.date desc
    """)
    List<Matches> findBySeasonIdOrderByDateDesc(@Param("seasonId") Integer seasonId);


    @Query("""
        SELECT t
        FROM team_match_stats t
        WHERE t.id.matchId = :matchId
          AND t.id.teamId = :teamId
              AND t.inningsNumber between 1 and 2
    """)
    Optional<TeamMatchStats> findTeamMatchStats(
            @Param("matchId") Integer matchId,
            @Param("teamId") Integer teamId
    );


}
