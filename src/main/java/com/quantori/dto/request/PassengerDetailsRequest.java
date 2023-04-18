package com.quantori.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class PassengerDetailsRequest {

    @JsonProperty("name")
    public String name;
    @JsonProperty("trips")
    public Integer trips;
    @JsonProperty("airline")
    public Integer airline;
}
