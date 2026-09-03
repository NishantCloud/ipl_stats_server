package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findPlayerByCountryCode(String countryCode);
}
