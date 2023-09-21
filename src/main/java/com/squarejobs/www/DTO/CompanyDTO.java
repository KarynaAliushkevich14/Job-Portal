package com.squarejobs.www.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // ?
public class CompanyDTO {
    @JsonProperty("pk_company")
    private Long pkCompany;
    @JsonProperty("companyAccount") // это название имеет значение только для json in postman
    private Long companyAccountId;
    @JsonProperty("offers")
    private List<OfferDTO> offers;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_size")
    private Integer companySize;
    @JsonProperty("nip")
    private String nip;
    @JsonProperty("introduction_date")
    private LocalDateTime introductionDate;
    @JsonProperty("state")
    private String state;
    @JsonProperty("brand_story")
    private String brandStory;
    @JsonProperty("country")
    private String country;
    @JsonProperty("province")
    private String province;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;
    @JsonProperty("nr_street")
    private Integer nrStreet;
    @JsonProperty("nr_building")
    private Integer nrBuilding;

}
