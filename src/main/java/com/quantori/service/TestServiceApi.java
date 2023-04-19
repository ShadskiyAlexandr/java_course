package com.quantori.service;

import com.quantori.constants.ServiceEndpoints;
import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.utils.RestHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class TestServiceApi extends RestHelper {

    public TestServiceApi() {
        super();
        headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + getToken());
    }

    @Step("GET - Get passenger by Id - {id}")
    public Response getPassengerById(String id) {
        return get(String.format(ServiceEndpoints.PASSENGER_BY_ID, id));
    }

    @Step("POST - create new passenger")
    public Response createPassenger(PassengerDetailsRequest passenger) {
        return post(ServiceEndpoints.PASSENGER, passenger);
    }

    @Step("DELETE - Delete passenger by Id - {id}")
    public Response deletePassengerById(String id) {
        return delete(String.format(ServiceEndpoints.PASSENGER_BY_ID, id));
    }

    @Step("PUT - Update passenger by Id - {id}")
    public Response updatePassengerById(String id, PassengerDetailsRequest newPassengerDetails) {
        return put(String.format(ServiceEndpoints.PASSENGER_BY_ID, id), newPassengerDetails);
    }

    public Response login() {
        loadProperties();
        Map<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", "password");
        formParams.put("scope", "offline_access");
        formParams.put("client_id", props.getProperty("client.id"));
        formParams.put("username", props.getProperty("user.name"));
        formParams.put("password", props.getProperty("user.password"));

        return postUrlEncoded("v1/token", formParams);
    }

    public String getToken() {
        return login()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("access_token");
    }
}
