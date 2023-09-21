package com.squarejobs.www.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "offer"
)
public class Offer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "offer_sequence"
    )
    @SequenceGenerator(
            name = "offer_sequence",
            sequenceName = "offer_sequence",
            allocationSize = 1
    )
    @Column(
            name = "pk_offer",
            updatable = false
    )
    private Long pkOffer;
    @ManyToOne
    @JoinColumn(name="pk_company")
    private Company company;
    @Column(
            name = "offer_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String offerName;
    @ManyToMany
    @JoinTable( // join table указывает на то что это кросс таблица; join column что мы связываем fk в этой таблице с другой таблицей.
            name = "offer_tech",
            joinColumns = @JoinColumn(name = "fk_offer"),
            inverseJoinColumns = @JoinColumn(name = "fk_technology"))
    private List<Technology> technologies;
    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;
    @Column(
            name = "salary_year",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String salaryYear;
    @Column(
            name = "salary_hour",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String salaryHour;
    @Column(
            name = "salary_undisc",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String salaryUndisclosed;
    @Column(name = "salary_status",
            nullable = false
    )
    private Integer salaryStatus; // is salary known
    @Column(
            name = "salary_level",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String level;
    @Column(
            name = "contract",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String contract;  //UoP, UoZ, B2B, UoD
    @Column(
            name = "introduction_date",
            nullable = false
    )
    private LocalDateTime introductionDate;
    @Column(name = "photo_background")
    private byte[] photoBackground;
    @Column(
            name = "project_description",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String projectDescription;
    @Column(
            name = "requirenments",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String requirenments;
    @Column(
            name = "company_offers",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String companyOffers;
    @Column(
            name = "additional_info",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String additionalInfo;
    @Column(
            name = "company_benefits",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String companyBenefits;
    @Column(
            name = "company_facilities",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String companyFacilities;
}
