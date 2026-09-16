package org.monexa.ipl_stats.Repositories;

import org.monexa.ipl_stats.entity.Deliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer> {

    List<Deliveries> findAllByInningsIdOrderByDeliveryIdAsc(int inningsId);

    @Query("""
                SELECT DISTINCT d
                FROM deliveries d
                JOIN innings i ON d.inningsId = i.inningsId
                LEFT JOIN FETCH d.wickets w
                LEFT JOIN FETCH w.fielders f
                WHERE i.matchId = :matchId
                ORDER BY d.deliveryId ASC
            """)
    List<Deliveries> findAllDeliveriesByMatchId(
            @Param("matchId") int matchId
    );
}
