package org.monexa.ipl_stats.Controllers;

import org.monexa.ipl_stats.Services.SeasonServices;
import org.monexa.ipl_stats.dto.SeasonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/season")
public class SeasonController {

    @Autowired
    SeasonServices seasonService;
    @GetMapping("/all")
    public ResponseEntity<List<SeasonResponse>> getAllSeasons() {

        return ResponseEntity.ok(seasonService.getAllSeasons());
    }
}
