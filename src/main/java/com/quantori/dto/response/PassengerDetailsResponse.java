package com.quantori.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetailsResponse {


    @JsonProperty("_id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("trips")
    public Integer trips;
    @JsonProperty("airline")
    public List<PassengerAirlineInfo> airline = new ArrayList<>();
    @JsonProperty("__v")
    public Integer v;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PassengerAirlineInfo {

        @JsonProperty("id")
        public Integer id;
        @JsonProperty("name")
        public String name;
        @JsonProperty("country")
        public String country;
        @JsonProperty("logo")
        public String logo;
        @JsonProperty("slogan")
        public String slogan;
        @JsonProperty("head_quaters")
        public String headQuaters;
        @JsonProperty("website")
        public String website;
        @JsonProperty("established")
        public String established;

    }

}


