package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.Innings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InningsRepository extends JpaRepository<Innings, Integer> {

    List<Innings> findAllInningsByMatchIdOrderByInningsIdAsc(int matchId);
}
