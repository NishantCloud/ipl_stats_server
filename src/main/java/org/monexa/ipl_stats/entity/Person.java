package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "people")
@Entity(name = "people")
public class Person {
    @Id
    @Column(name = "person_id")
    Integer id;

    @Column(name = "full_name")
    String fullName;

}
