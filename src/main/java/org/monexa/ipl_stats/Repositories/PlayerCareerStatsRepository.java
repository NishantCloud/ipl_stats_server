package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto;
import org.monexa.ipl_stats.entity.PlayerCareerStats;
import org.monexa.ipl_stats.entity.PlayerCareerStatsId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerCareerStatsRepository extends JpaRepository<PlayerCareerStats, PlayerCareerStatsId> {
    PlayerCareerStats findByIdPersonId(int personId);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.runsScored
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.runsScored DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopRuns(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.wicketsTaken
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.wicketsTaken DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopWickets(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.sixes
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.sixes DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopSixes(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.fours
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.fours DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopFours(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.hundreds
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.hundreds DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopHundreds(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.fifties
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.fifties DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopFifties(Pageable pageable);

    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.catches
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.catches DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopCatches(Pageable pageable);
    @Query("""
        SELECT new org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto(
            cs.id.personId,
            pp.displayName,
            -1,
            'ALL',
            cs.stumpings
        )
        FROM PlayerCareerStats cs
        JOIN PlayerProfiles pp
            ON pp.personId = cs.id.personId
        ORDER BY cs.stumpings DESC
    """)
    List<PlayerSeasonStatsOverviewDto> findTopStumpings(Pageable pageable);
}
