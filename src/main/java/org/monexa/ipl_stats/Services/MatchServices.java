package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Repositories.DeliveriesRepository;
import org.monexa.ipl_stats.Repositories.InningsRepository;
import org.monexa.ipl_stats.Repositories.MatchRepository;
import org.monexa.ipl_stats.dto.DeliveriesDto;
import org.monexa.ipl_stats.dto.DeliveriesResponse;
import org.monexa.ipl_stats.dto.InningsDto;
import org.monexa.ipl_stats.dto.WicketDto;
import org.monexa.ipl_stats.entity.Deliveries;
import org.monexa.ipl_stats.entity.DeliveryWickets;
import org.monexa.ipl_stats.entity.DeliveryWicketsFielders;
import org.monexa.ipl_stats.entity.Innings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServices {
    @Autowired
    MatchRepository matchRepo;

    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    DeliveriesRepository deliveriesRepository;


    public DeliveriesResponse getDeliveriesOfMatch(int matchId) {

        List<DeliveriesDto> deliveriesDtoList = new ArrayList<>();
        List<InningsDto> inningsDtoList = new ArrayList<>();
        for(Innings innings: inningsRepository.findAllInningsByMatchIdOrderByInningsIdAsc(matchId)){
            inningsDtoList.add(
              new InningsDto(
                      innings.getInningsId(), innings.getInningsNumber(), innings.getMatchId(), innings.getBattingTeamId(), innings.isSuperOver(),
                      innings.getTargetRuns(), innings.getTargetOvers()
              )
            );
        }
        for (Deliveries deliveries: deliveriesRepository.findAllDeliveriesByMatchId(matchId)) {

            WicketDto wicketDto = null;
            if(deliveries.isWicket()){
                DeliveryWickets deliveryWickets = deliveries.getWickets().stream().findFirst().get();
                Integer fielderId =   !deliveryWickets.getFielders().isEmpty()? deliveryWickets.getFielders().stream().findFirst().get().getPersonId(): null;

                wicketDto = new WicketDto(deliveryWickets.getWicketId(), deliveryWickets.getPlayerOutId(), fielderId, deliveryWickets.getDismissalKind());
            }
            deliveriesDtoList.add(new DeliveriesDto(
                    deliveries.getInningsId(), deliveries.getOverNumber(), deliveries.getBallSequence(), deliveries.getLegalBallNumber(),deliveries.getBatterId(),
                    deliveries.getBowlerId(), deliveries.getNonStrikerId(),deliveries.getRunsBatter(), deliveries.getRunsExtras(),deliveries.getRunsTotal(),deliveries.getExtraType(),
                    deliveries.isWicket(), wicketDto
            ));
        }


        return new DeliveriesResponse(
                inningsDtoList,
                deliveriesDtoList
        ) ;
    }
}
