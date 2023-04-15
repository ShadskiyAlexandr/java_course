package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerDetailsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@Epic(value = "Create passenger")
public class CreatePassengerTests extends BaseTest {

    @AfterEach
    void deleteTestData() {
        deletePassengerById();
    }

    @Test
    @Description(value = "Testing creating new passenger")
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
