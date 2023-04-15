package com.quantori;

import com.quantori.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic(value = "Delete passenger")
public class DeletePassengerByIdTests extends BaseTest {

    @BeforeEach
    void createTestData() {
        createNewPassenger();
    }

    @Test
    @Description(value = "Testing deleting passenger by Id")
    void deletePassengerByIdTest() {
        testServiceApi
                .deletePassengerById(passengerInfo.getId())
                .then()
                .spec(responseSpec);
    }
}
