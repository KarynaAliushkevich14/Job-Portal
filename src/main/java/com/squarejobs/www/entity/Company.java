package com.squarejobs.www.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "company"
)
public class Company {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @Column(
            name = "pk_company",
            updatable = false
    )
    private Long pkCompany;
    @Column(
            name = "company_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String companyName;
    @Column(name = "company_size")
    private Integer companySize;
    @Column(
            name = "nip",
            nullable = false,
            columnDefinition = "TEXT")
    private String nip;
    @Column(name = "introduction_date")
    private Date introductionDate;
    @Column(
            name = "state",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String state;
    @Column(
            name = "brand_story",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String brandStory;
    @OneToOne
    @JoinColumn(name = "pk_account") // -> указываем primary key для таблицы с которой связываем
    private CompanyAccount companyAccount;
    @Column(
            name = "country",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String country;
    @Column(
            name = "province",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String province;
    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;
    @Column(
            name = "street",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String street;
    @Column(name = "nr_street")
    private Integer nrStreet;
    @Column(name = "nr_building")
    private Integer nrBuilding;

    public Company(String companyName, Integer companySize, String nip, Date introductionDate, String state,
                   String brandStory, CompanyAccount companyAccount, String country, String province, String city, String street,
                   Integer nrStreet, Integer nrBuilding) {
        this.companyName = companyName;
        this.companySize = companySize;
        this.nip = nip;
        this.introductionDate = introductionDate;
        this.state = state;
        this.brandStory = brandStory;
        this.companyAccount = companyAccount;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.nrStreet = nrStreet;
        this.nrBuilding = nrBuilding;
    }

    public Company() {
    }
}
/** Для владельца фирмы:
 * впиши название фирмы
 * */
