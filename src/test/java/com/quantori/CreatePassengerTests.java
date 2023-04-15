package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerDetailsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class CreatePassengerTests extends BaseTest {


    @AfterEach
    void deleteTestData() {
        deletePassengerById();
    }

    @Test
    void createPassengerTest() {
        passengerInfo =
                testServiceApi
                        .createPassenger(passenger)
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response()
                        .as(PassengerDetailsResponse.class);
    }
}
