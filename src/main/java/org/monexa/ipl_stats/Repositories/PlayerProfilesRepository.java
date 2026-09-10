package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.PlayerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerProfilesRepository extends JpaRepository<PlayerProfiles, Integer> {

    List<PlayerProfiles> findByCountryCode(String countryCode);

    PlayerProfiles findByPersonId(Integer personId);
}
