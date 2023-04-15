package com.quantori.service;

import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.utils.RestHelper;
import io.restassured.response.Response;

public class TestServiceApi extends RestHelper {

    public Response getPassengerById(String id) {
        return get(String.format("/%s", id));
    }

    public Response createPassenger(PassengerDetailsRequest passenger) {
        return post(passenger);
    }

    public Response deletePassengerById(String id) {
        return delete(String.format("/%s", id));
    }

    public Response updatePassengerById(String id, PassengerDetailsRequest newPassengerDetails) {
        return put(String.format("/%s", id), newPassengerDetails);
    }
}
