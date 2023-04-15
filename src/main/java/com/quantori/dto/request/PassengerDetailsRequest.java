package com.quantori.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@With
public class PassengerDetailsRequest {

    @JsonProperty("name")
    public String name;
    @JsonProperty("trips")
    public Integer trips;
    @JsonProperty("airline")
    public Integer airline;

}
