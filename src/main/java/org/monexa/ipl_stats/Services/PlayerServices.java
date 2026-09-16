package org.monexa.ipl_stats.Services;

import lombok.extern.java.Log;
import org.jspecify.annotations.NonNull;
import org.monexa.ipl_stats.Repositories.PlayerCareerStatsRepository;
import org.monexa.ipl_stats.Repositories.PlayerSeasonStatsRepository;
import org.monexa.ipl_stats.dto.*;
import org.monexa.ipl_stats.entity.PlayerCareerStats;
import org.monexa.ipl_stats.entity.PlayerProfiles;
import org.monexa.ipl_stats.Repositories.PlayerProfilesRepository;
import org.monexa.ipl_stats.entity.PlayerSeasonStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServices {

    @Autowired
    PlayerProfilesRepository playerRepo;
    @Autowired
    PlayerCareerStatsRepository playerCareerStatsRepository;
    @Autowired
    PlayerSeasonStatsRepository playerSeasonStatsRepository;

    public List<PlayerItemDto> getTopPlayer(int count) {

        List<PlayerItemDto> list =new ArrayList<>();
        for(PlayerProfiles p: playerRepo.findByCountryCode("IND")){
            boolean isBowler = p.getPlayingRole().equals("BL");
            PlayerCareerStats playerCareerStats = playerCareerStatsRepository.findByIdPersonId(p.getPersonId());
            if(playerCareerStats==null) {
                continue;
            }
            list.add(new PlayerItemDto(p.getPersonId(),p.getName(),p.getFullName(),p.getImageUrl(),p.getCountryCode(),p.getCountryName(),p.getTeams().getTeamName(),p.getTeams().getShortName(),p.getTeams().getTeamId(),p.getPlayingRole(),
                        isBowler,50, playerCareerStats.getRunsScored(), playerCareerStats.getWicketsTaken()));
        }
        list.sort(Comparator.comparingInt(PlayerItemDto::runsScored).reversed());
        return list;
    }

    public PlayerProfileResponse getPlayerProfile(Integer personId) {

        PlayerProfiles p = playerRepo.findByPersonId(personId);
        PlayerCareerStats playerCareerStats =  playerCareerStatsRepository.findByIdPersonId(personId);
        List<PlayerSeasonStats> playerSeasonStatsList = playerSeasonStatsRepository.findByPersonIdWithSeasonAndTournament(personId).get();


        PlayerProfileResponse response = new PlayerProfileResponse();
        response.setPlayerProfile(getPlayerProfileDto(p));
        response.setPlayerCareerStats(mapCareerStats(playerCareerStats));
        response.setPlayerSeasonStatsList(playerSeasonStatsList.stream().map(this::mapSeasonStats).toList());

        return response;
    }

    private PlayerCareerStatsDto mapCareerStats(PlayerCareerStats p){
        return new PlayerCareerStatsDto(
                p.getId().getTournamentId(),p.getMatchesPlayed(),p.getInningsBatted(),p.getRunsScored(),p.getBallsFaced(),p.getFours(),p.getSixes(),
                p.getFifties(),p.getHundreds(),p.getHighestScore(),p.getTimesOut(),p.getInningsBowled(),p.getBallsBowled(),p.getRunsConceded(),p.getWicketsTaken(),p.getBestBowlingWickets(),p.getBestBowlingRuns(),
                p.getCatches(),p.getStumpings()
        );
    }
    private PlayerSeasonStatsDto mapSeasonStats(PlayerSeasonStats p) {
        return new PlayerSeasonStatsDto(
                p.getTournament().getTournamentId(),p.getTournament().getShortName(),p.getId().getSeasonId(),p.getSeason().getYear(),p.getMatchesPlayed(),p.getInningsBatted(),
                p.getRunsScored(),p.getBallsFaced(),p.getFours(),p.getSixes(),p.getFifties(),
                p.getHundreds(),p.getHighestScore(),p.getTimesOut(),p.getInningsBowled(),p.getBallsBowled(),
                p.getRunsConceded(),p.getWicketsTaken(),p.getBestBowlingWickets(), p.getBestBowlingRuns() ,p.getCatches(),p.getStumpings()
        );
    }


    private static @NonNull PlayerProfileDto getPlayerProfileDto(PlayerProfiles p) {
        boolean isBowler = p.getPlayingRoleCode().equals("BL");
        int formScore = 50;
        String formLabel= "GOOD";
        String playerImageUrl = "api/player/"+ p.getPersonId()+"/"+ p.getTeams().getTeamId()+"/image";

        PlayerProfileDto playerProfileDto =  new PlayerProfileDto(
                p.getPersonId(), p.getName(), p.getFullName(), p.getCountryName(),playerImageUrl, p.getCountryCode(), p.getTeams().getTeamId(),isBowler, formScore,formLabel
                , p.getPlayingRole(), p.getPlayingRoleCode(), p.getTeams().getTeamName(), p.getTeams().getShortName(), p.getBattingStyleShort(), p.getBowlingStyle(),p.getBowlingStyleShort()
        );
        return playerProfileDto;
    }
}
