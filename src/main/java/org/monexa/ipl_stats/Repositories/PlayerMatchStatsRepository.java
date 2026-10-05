package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.dto.MatchDetailsResponse;
import org.monexa.ipl_stats.entity.Person;
import org.monexa.ipl_stats.entity.PlayerMatchStats;
import org.monexa.ipl_stats.entity.PlayerMatchStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMatchStatsRepository
        extends JpaRepository<PlayerMatchStats, PlayerMatchStatsId> {

    @Query("""
            SELECT p
            FROM player_match_stats p
            WHERE p.id.person.id = :personId
              AND p.id.matchId = :matchId
            """)
    PlayerMatchStats findByIdPersonIdAndIdMatchId(
            @Param("personId") int personId,
            @Param("matchId") int matchId
    );
    @Query("""
        SELECT new org.monexa.ipl_stats.dto.MatchDetailsResponse$Batting(
            id.person.id,
            id.person.fullName,
            CASE
                WHEN isOut = true THEN 'OUT'
                ELSE 'NOT OUT'
            END,
            runsScored,
            ballsFaced,
            fours,
            sixes,
            (runsScored * 100.0) / ballsFaced,
            isOut
        )
        FROM player_match_stats
        WHERE id.matchId = :matchId
          AND teamId = :teamId
          AND ballsFaced > 0
        ORDER BY battingPosition ASC
        """)
    List<MatchDetailsResponse.Batting> findBattingOfPlayerOfMatchOfTeam(
            @Param("matchId") int matchId,
            @Param("teamId") int teamId
    );


    @Query("""
        SELECT new org.monexa.ipl_stats.dto.MatchDetailsResponse$Bowling(
            id.person.id,
            id.person.fullName,
            CONCAT(
                FLOOR(ballsBowled / 6),
                '.',
                MOD(ballsBowled, 6)
            ),
            maidens,
            wicketsTaken,
            (runsConceded * 6.0) / ballsBowled
        )
        FROM player_match_stats
        WHERE id.matchId = :matchId
          AND teamId = :teamId
          AND ballsBowled > 0
        ORDER BY wicketsTaken DESC
        """)
    List<MatchDetailsResponse.Bowling> findBowlingOfPlayerOfMatchOfTeam(
            @Param("matchId") int matchId,
            @Param("teamId") int teamId
    );


}