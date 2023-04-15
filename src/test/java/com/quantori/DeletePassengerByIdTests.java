package com.quantori;

import com.quantori.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeletePassengerByIdTests extends BaseTest {

    @BeforeEach
    void createTestData() {
        createNewPassenger();
    }

    @Test
    void deletePassengerByIdTest() {
        testServiceApi
                .deletePassengerById(passengerInfo.getId())
                .then()
                .spec(responseSpec);
    }
}
