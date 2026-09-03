package org.monexa.ipl_stats.Controllers;


import org.monexa.ipl_stats.Models.MatchItem;
import org.monexa.ipl_stats.Models.Player;
import org.monexa.ipl_stats.Services.MatchServices;
import org.monexa.ipl_stats.Services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayerServices playerServices;

    @GetMapping("/all")
    public List<Player> getAllPlayer(){

        return playerServices.getTopPlayer(10);
    }
}
