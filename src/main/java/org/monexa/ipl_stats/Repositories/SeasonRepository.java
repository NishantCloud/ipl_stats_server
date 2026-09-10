package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Seasons, Integer> {
    List<Seasons> findAllByOrderByYearDesc();
}
