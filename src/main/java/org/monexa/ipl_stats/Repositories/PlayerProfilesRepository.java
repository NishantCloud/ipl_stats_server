package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.dto.MatchDetailsResponse;
import org.monexa.ipl_stats.dto.PlayerItemResponse;
import org.monexa.ipl_stats.entity.PlayerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerProfilesRepository extends JpaRepository<PlayerProfiles, Integer> {

    List<PlayerProfiles> findByCountryCode(String countryCode);

    PlayerProfiles findByPersonId(Integer personId);

    @Query( """
        select new org.monexa.ipl_stats.dto.MatchDetailsResponse$Squad(
            pp.personId,
            pp.battingName,
            pp.playingRoleCode,
            false,
            false,
            false
        ) from playing_xi px join PlayerProfiles pp on pp.personId = px.personId
        where px.matchId = :matchId and px.teamId = :teamId order by px.playingXiId asc
""")
    List<MatchDetailsResponse.Squad> findPlayingXi(@Param("matchId") int matchId, @Param("teamId") int teamId);
//    @Query(
//            "Select * "
//    )
//    PlayerItemResponse findPlayerItemResponseByPersonId(int personId);
}
