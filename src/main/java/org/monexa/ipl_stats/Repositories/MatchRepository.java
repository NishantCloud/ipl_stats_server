package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Matches,Integer> {


}
