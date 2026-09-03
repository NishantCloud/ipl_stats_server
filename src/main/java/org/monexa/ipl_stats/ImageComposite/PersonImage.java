package org.monexa.ipl_stats.ImageComposite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "people_images")
@Getter
@Setter
@NoArgsConstructor
public class PersonImage {

    @Id
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "image_width", nullable = false)
    private Integer imageWidth;

    @Column(name = "image_height", nullable = false)
    private Integer imageHeight;

    @Column(name = "neck_anchor_x", nullable = false, precision = 6, scale = 5)
    private BigDecimal neckAnchorX;

    @Column(name = "neck_anchor_y", nullable = false, precision = 6, scale = 5)
    private BigDecimal neckAnchorY;

    @Column(name = "neck_width_fraction", nullable = false, precision = 6, scale = 5)
    private BigDecimal neckWidthFraction;

    // NOT @Version -- plain counter we bump manually to invalidate cached composites
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