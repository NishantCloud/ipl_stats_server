package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Models.Player;
import org.monexa.ipl_stats.Repositories.MatchRepository;
import org.monexa.ipl_stats.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServices {

    @Autowired
    PlayerRepository playerRepo;
    public List<Player> getTopPlayer(int count) {

        List<Player> list =new ArrayList<>();
        for(Player player: playerRepo.findPlayerByCountryCode("IND")){
            int p = player.getPersonId();
            if (p==11||p==51||p==309 || p==50) list.add(player);
        }
        return list;
    }

}
