package com.squarejobs.www.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "technology"
)
public class Technology {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "technology_sequence"
    )
    @SequenceGenerator(
            name = "technology_sequence",
            sequenceName = "technology_sequence",
            allocationSize = 1
    )
    @Column(
            name = "pk_technology",
            updatable = false
    )
    private Long pkTechnology;
    @Column(
            name = "name_technology",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String technologyName;
    @ManyToMany(mappedBy = "technologies")
    private List<Offer> offers;
}
