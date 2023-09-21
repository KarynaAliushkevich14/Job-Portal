package com.squarejobs.www.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyAccountDTO {
    @JsonProperty("pk_account") // так будет называться поле в Postman
    private Long pkAccount;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email_status")
    private int emailStatus;
    //@JsonProperty("photo")
    //private byte[] photo;
    @JsonProperty("fk_company")
    private CompanyDTO company;
}
