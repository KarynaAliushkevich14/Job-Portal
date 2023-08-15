package com.squarejobs.www.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnologyDTO {

    @JsonProperty("pk_technology")
    private Long pkTechnology;
    @JsonProperty("name_technology")
    private String technologyName;
}
