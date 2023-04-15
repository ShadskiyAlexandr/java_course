package com.quantori;

import com.quantori.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic(value = "Update passenger")
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
    @Description(value = "Testing getting passenger by Id")

    void getDetailsInfoTest() {
        testServiceApi
                .updatePassengerById(passengerInfo.getId(), passenger)
                        .prettyPeek()
                        .then()
                        .spec(responseSpec);
    }
}

