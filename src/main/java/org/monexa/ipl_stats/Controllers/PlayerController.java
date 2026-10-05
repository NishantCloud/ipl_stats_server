package org.monexa.ipl_stats.Controllers;


import org.monexa.ipl_stats.dto.PlayerItemDto;
import org.monexa.ipl_stats.dto.PlayerItemResponse;
import org.monexa.ipl_stats.dto.PlayerProfileResponse;
import org.monexa.ipl_stats.Services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayerServices playerServices;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerItemDto>> getAllPlayer() {

        return ResponseEntity.ok(playerServices.getTopPlayer(10));
    }

    @GetMapping("/item/{personId}")
    public ResponseEntity<PlayerItemResponse> getPlayerItem(int personId){
        return ResponseEntity.ok(playerServices.getPlayerItem(personId));
    }
    @GetMapping("/{personId}")
    public PlayerProfileResponse getPlayerProfile(
            @PathVariable Integer personId
    ) {

        return playerServices.getPlayerProfile(personId);
    }
}
