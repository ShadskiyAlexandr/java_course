package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerDetailsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadPassengerDetailsByIdTests extends BaseTest {

    @BeforeEach
    void createTestData() {
        createNewPassenger();
    }

    @AfterEach
    void deleteTestData() {
        deletePassengerById();
    }

    @Test
    void getDetailsInfoTest() {
        PassengerDetailsResponse passengerResponse =
                testServiceApi
                        .getPassengerById(passengerInfo.getId())
                        .prettyPeek()
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .as(PassengerDetailsResponse.class);
    }
}
