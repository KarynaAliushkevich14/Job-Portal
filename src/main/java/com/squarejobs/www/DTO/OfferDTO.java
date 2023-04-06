package com.squarejobs.www.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
//@ToString -  возвращает строковое представление объекта, которое может быть использовано для вывода информации об объекте в логи. Lombok сгенерирует метод toString(), который будет включать все поля объекта и их значения в строковом представлении.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDTO {
    @JsonProperty("pk_offer")
    private Long pkOfferDto;
    @JsonProperty("company")
    private Long companyId;
    @JsonProperty("offer_name")
    private String offerName;
    @JsonProperty("city")
    private String city;
    @JsonProperty("salary_year")
    private String salaryYear;
    @JsonProperty("salary_hour")
    private String salaryHour;
    @JsonProperty("salary_undisc")
    private String salaryUndisclosed;
    @JsonProperty("salary_status")
    private Integer salaryStatus;
    @JsonProperty("salary_level")
    private String salaryLevel;
    @JsonProperty("contract")
    private String contract;
    @JsonProperty("introduction_date")
    private LocalDateTime introductionDate;
    @JsonProperty("photo_background")
    private byte[] photoBackground;
    @JsonProperty("technology")
    private String technology;
    @JsonProperty("project_description")
    private String projectDescription;
    @JsonProperty("requirenments")
    private String requirenments;
    @JsonProperty("company_offers")
    private String companyOffers;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("company_benefits")
    private String companyBenefits;
    @JsonProperty("company_facilities")
    private String companyFacilities;
}
