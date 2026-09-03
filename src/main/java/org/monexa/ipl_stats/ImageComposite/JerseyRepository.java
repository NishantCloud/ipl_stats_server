package org.monexa.ipl_stats.ImageComposite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JerseyRepository extends JpaRepository<Jersey, Long> {
    // findById(teamId) already works since team_id IS the @Id
}
