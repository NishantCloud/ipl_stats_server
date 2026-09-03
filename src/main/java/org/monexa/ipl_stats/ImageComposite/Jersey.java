package org.monexa.ipl_stats.ImageComposite;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "jerseys")
@Getter
@Setter
@NoArgsConstructor
public class Jersey {

    @Id
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "image_width", nullable = false)
    private Integer imageWidth;

    @Column(name = "image_height", nullable = false)
    private Integer imageHeight;

    @Column(name = "collar_anchor_x", nullable = false, precision = 6, scale = 5)
    private BigDecimal collarAnchorX;

    @Column(name = "collar_anchor_y", nullable = false, precision = 6, scale = 5)
    private BigDecimal collarAnchorY;

    @Column(name = "collar_width_fraction", nullable = false, precision = 6, scale = 5)
    private BigDecimal collarWidthFraction;

    @Column(name = "image_version", nullable = false)
    private Integer imageVersion = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    void onCreate() { createdAt = OffsetDateTime.now(); updatedAt = createdAt; }

    @PreUpdate
    void onUpdate() { updatedAt = OffsetDateTime.now(); }
}