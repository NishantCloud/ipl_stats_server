package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto;
import org.monexa.ipl_stats.entity.PlayerCareerStats;
import org.monexa.ipl_stats.entity.PlayerCareerStatsId;
import org.monexa.ipl_stats.entity.PlayerSeasonStats;
import org.monexa.ipl_stats.entity.PlayerSeasonStatsId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerSeasonStatsRepository extends JpaRepository<PlayerSeasonStats, PlayerSeasonStatsId> {
    @Query("""
        SELECT ps
        FROM PlayerSeasonStats  ps
       JOIN FETCH ps.season
        JOIN FETCH ps.tournament
        WHERE ps.id.personId = :personId
            ORDER BY ps.season.year DESC
    """)
    Optional<List<PlayerSeasonStats>> findByPersonIdWithSeasonAndTournament(
            @Param("personId") Integer personId
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.runsScored
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.runsScored DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopRuns(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.wicketsTaken
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.wicketsTaken DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopWickets(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.sixes
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.sixes DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopSixes(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.fours
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.fours DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopFours(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.hundreds
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.hundreds DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopHundreds(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.fifties
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.fifties DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopFifties(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.catches
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.catches DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopCatches(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
        cs.id.personId,
            pp.teams.teamId,
        pp.displayName,
        cs.id.seasonId,
        cs.season.seasonName,
        cs.stumpings
    )
    FROM PlayerSeasonStats cs
    JOIN PlayerProfiles pp
        ON pp.personId = cs.id.personId
    WHERE cs.id.seasonId = :seasonId
    ORDER BY cs.stumpings DESC
""")
    List<PlayerSeasonStatsOverviewDto> findTopStumpings(
            @Param("seasonId") int seasonId,
            Pageable pageable
    );

}
