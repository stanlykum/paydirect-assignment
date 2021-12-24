package com.paydirect.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"name", "description", "habitat", "isLegendary"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    private String name;
    private String description;
    private String habitat;
    @JsonProperty("isLegendary")
    private boolean isLegendary;
}
