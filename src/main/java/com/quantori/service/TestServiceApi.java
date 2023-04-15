package com.quantori.service;

import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.utils.RestHelper;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class TestServiceApi extends RestHelper {

    @Step("GET - Get passenger by Id - {id}")
    public Response getPassengerById(String id) {
        return get(String.format("/%s", id));
    }

    @Step("POST - create new passenger")
    public Response createPassenger(PassengerDetailsRequest passenger) {
        return post(passenger);
    }

    @Step("DELETE - Delete passenger by Id - {id}")
    public Response deletePassengerById(String id) {
        return delete(String.format("/%s", id));
    }

    @Step("PUT - Update passenger by Id - {id}")
    public Response updatePassengerById(String id, PassengerDetailsRequest newPassengerDetails) {
        return put(String.format("/%s", id), newPassengerDetails);
    }
}
