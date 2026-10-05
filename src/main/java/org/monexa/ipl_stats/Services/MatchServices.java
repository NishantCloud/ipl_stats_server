package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Repositories.*;
import org.monexa.ipl_stats.dto.*;
import org.monexa.ipl_stats.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServices {
    @Autowired
    MatchRepository matchRepo;
    @Autowired
    PlayerProfilesRepository playerProfilesRepo;
    @Autowired
    PlayerMatchStatsRepository playerMatchStatsRepo;

    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    DeliveriesRepository deliveriesRepository;


    public DeliveriesResponse getDeliveriesOfMatch(int matchId) {

        List<DeliveriesDto> deliveriesDtoList = new ArrayList<>();
        List<InningsDto> inningsDtoList = new ArrayList<>();
        for (Innings innings : inningsRepository.findAllInningsByMatchIdOrderByInningsIdAsc(matchId)) {
            inningsDtoList.add(
                    new InningsDto(
                            innings.getInningsId(), innings.getInningsNumber(), innings.getMatchId(), innings.getBattingTeamId(), innings.isSuperOver(),
                            innings.getTargetRuns(), innings.getTargetOvers()
                    )
            );
        }
        for (Deliveries deliveries : deliveriesRepository.findAllDeliveriesByMatchId(matchId)) {

            WicketDto wicketDto = null;
            if (deliveries.isWicket()) {
                DeliveryWickets deliveryWickets = deliveries.getWickets().stream().findFirst().get();
                Integer fielderId = !deliveryWickets.getFielders().isEmpty() ? deliveryWickets.getFielders().stream().findFirst().get().getPerson().getId() : null;
                String fielderName = !deliveryWickets.getFielders().isEmpty() ? deliveryWickets.getFielders().stream().findFirst().get().getPerson().getFullName() : null;

                wicketDto = new WicketDto(deliveryWickets.getWicketId(), deliveryWickets.getPlayerOut().getId(), deliveryWickets.getPlayerOut().getFullName(), fielderId, fielderName, deliveryWickets.getDismissalKind());
            }
            deliveriesDtoList.add(new DeliveriesDto(
                    deliveries.getInningsId(), deliveries.getOverNumber(), deliveries.getBallSequence(), deliveries.getLegalBallNumber(), deliveries.getBatter().getId(), deliveries.getBatter().getFullName(),
                    deliveries.getBowler().getId(), deliveries.getBowler().getFullName(), deliveries.getNonStriker().getId(), deliveries.getNonStriker().getFullName(), deliveries.getRunsBatter(), deliveries.getRunsExtras(), deliveries.getRunsTotal(), deliveries.getExtraType(),
                    deliveries.isWicket(), wicketDto
            ));
        }


        return new DeliveriesResponse(
                inningsDtoList,
                deliveriesDtoList
        );
    }

    public List<MatchItemResponse> getMatchList(int seasonId) {
        List<MatchItemResponse> list = new ArrayList<>();
        for (Matches matches : matchRepo.findBySeasonIdOrderByDateDesc(seasonId)) {


            TeamMatchStats team1MatchStats = matchRepo.findTeamMatchStats(matches.getMatchId(), matches.getTeam1().getTeamId()).orElse(null);
            TeamMatchStats team2MatchStats = matchRepo.findTeamMatchStats(matches.getMatchId(), matches.getTeam2().getTeamId()).orElse(null);

            if (team2MatchStats == null || team1MatchStats == null)
                System.out.println("ERROR : " + matches.getMatchId());
            String team1Score = team1MatchStats == null ? "0/0 (20)" : team1MatchStats.getTotalRuns() + "/" + team1MatchStats.getTotalWickets() + " (" + team1MatchStats.getOversPlayed() + ")";
            String team2Score = team2MatchStats == null ? "0/0 (20)" : team2MatchStats.getTotalRuns() + "/" + team2MatchStats.getTotalWickets() + " (" + team2MatchStats.getOversPlayed() + ")";
            String wonText =
                    matches.getWinnerTeam() == null ? "Match Abandoned"
                            : matches.getResultType().equals("tie".toLowerCase())
                            ? "Match Tied "
                            : matches.getWinnerTeam().getShortName() + " won by " +
                            (matches.getWinByRuns() == null
                                    ? matches.getWinByWickets() + " Wickets"
                                    : matches.getWinByRuns() + " Runs");
            list.add(new MatchItemResponse(
                    matches.getMatchId(), matches.getTeam1().getTeamName(), team1Score,
                    matches.getTeam2().getTeamName(), team2Score,
                    matches.getWinnerTeam() == null ? "NOT DECIDED" : matches.getWinnerTeam().getTeamName(), matches.getDate(),
                    wonText,
                    matches.getVenue().getVenueId(), matches.getVenue().getVenueName(), matches.getSeasons().getSeasonName()
                    , matches.getMatchStage() == null ? "MATCH " + matches.getMatchNumber() : matches.getMatchStage()
            ));
        }
        return list;
    }

    public MatchDetailsResponse getMatchDetails(int matchId) {

        Optional<Matches> dataMatch = matchRepo.findById(matchId);

        if (dataMatch.isEmpty()) {
            return null;
        }
        Matches match = dataMatch.get();

        List<MatchDetailsResponse.Squad> team1Squad = playerProfilesRepo.findPlayingXi(match.getMatchId(), match.getTeam1().getTeamId());
        List<MatchDetailsResponse.Squad> team2Squad = playerProfilesRepo.findPlayingXi(match.getMatchId(), match.getTeam2().getTeamId());

        List<MatchDetailsResponse.Batting> inningsBattingTeam1 = playerMatchStatsRepo.findBattingOfPlayerOfMatchOfTeam(match.getMatchId(), match.getTeam1().getTeamId());
        List<MatchDetailsResponse.Batting> inningsBattingTeam2 = playerMatchStatsRepo.findBattingOfPlayerOfMatchOfTeam(match.getMatchId(), match.getTeam2().getTeamId());

        List<MatchDetailsResponse.Bowling> inningsBowlingTeam1 = playerMatchStatsRepo.findBowlingOfPlayerOfMatchOfTeam(match.getMatchId(), match.getTeam1().getTeamId());
        List<MatchDetailsResponse.Bowling> inningsBowlingTeam2 = playerMatchStatsRepo.findBowlingOfPlayerOfMatchOfTeam(match.getMatchId(), match.getTeam2().getTeamId());


        TeamMatchStats team1MatchStats = matchRepo.findTeamMatchStats(match.getMatchId(), match.getTeam1().getTeamId()).orElse(null);
        TeamMatchStats team2MatchStats = matchRepo.findTeamMatchStats(match.getMatchId(), match.getTeam2().getTeamId()).orElse(null);

        List<MatchDetailsResponse.Innings> innings = new ArrayList<>();

        innings.add(
                new MatchDetailsResponse.Innings(
                    match.getTeam1().getShortName(),
                    team1MatchStats.getTotalRuns() + "/" + team1MatchStats.getTotalWickets(),
                    team1MatchStats.getOversPlayed().toString(),
                    "()",
                    inningsBattingTeam1,
                    inningsBowlingTeam1
        ));

        innings.add(
                new MatchDetailsResponse.Innings(
                    match.getTeam2().getShortName(),
                    team2MatchStats.getTotalRuns() + "/" + team2MatchStats.getTotalWickets(),
                    team2MatchStats.getOversPlayed().toString(),
                    "()",
                    inningsBattingTeam2,
                    inningsBowlingTeam2
        ));

        String wonText =
                match.getWinnerTeam() == null ? "Match Abandoned"
                        : match.getResultType().equals("tie".toLowerCase())
                        ? "Match Tied "
                        : match.getWinnerTeam().getShortName() + " won by " +
                        (match.getWinByRuns() == null
                                ? match.getWinByWickets() + " Wickets"
                                : match.getWinByRuns() + " Runs");


        PlayerMatchStats potMatch = playerMatchStatsRepo.findByIdPersonIdAndIdMatchId(match.getPlayerOfMatch().getId(), match.getMatchId());
        String potSummary = potMatch.getBallsBowled()> 0 && potMatch.getWicketsTaken() > 0 ?
                            "w "+potMatch.getWicketsTaken() + " b " +potMatch.getBallsBowled()  + " r "+ potMatch.getRunsConceded()  + (potMatch.getCatches()>0 ? " & "+potMatch.getCatches() + "catch": "")
                            :
                            "r "+potMatch.getRunsScored() + (potMatch.isOut()?"":"*")+ " b (" +potMatch.getBallsFaced()  + ") " + (potMatch.getCatches()>0 ? " & "+potMatch.getCatches() + "catch": "");



        MatchDetailsResponse.PlayerOfMatch playerOfMatch = new MatchDetailsResponse.PlayerOfMatch(
                match.getPlayerOfMatch().getId(),
                match.getPlayerOfMatch().getFullName(),
                null,
                potSummary

        );

        return new MatchDetailsResponse(
                match.getMatchId(),
                match.getMatchStage()==null? "MATCH : "+match.getMatchNumber(): match.getMatchStage(),
                match.getVenue().getVenueName(),
                match.getDate().toString(),
                match.getTeam1().getShortName(),
                match.getTeam1().getTeamName(),
                team1MatchStats.getTotalRuns() + "/" + team1MatchStats.getTotalWickets()+" ("+ team1MatchStats.getOversPlayed()+")",
                match.getTeam2().getShortName(),
                match.getTeam2().getTeamName(),
                team2MatchStats.getTotalRuns() + "/" + team2MatchStats.getTotalWickets()+" ("+ team2MatchStats.getOversPlayed()+")",
                wonText,
                match.getTossWinner().getShortName(),
                match.getTossDecision(),
                playerOfMatch,
                innings,
                team1Squad,
                team2Squad



        );
    }
}
