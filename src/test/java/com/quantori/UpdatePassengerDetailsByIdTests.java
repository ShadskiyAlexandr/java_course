package com.quantori;

import com.quantori.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdatePassengerDetailsByIdTests extends BaseTest {

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
        testServiceApi
                .updatePassengerById(passengerInfo.getId(), passenger)
                        .prettyPeek()
                        .then()
                        .spec(responseSpec);
    }
}

