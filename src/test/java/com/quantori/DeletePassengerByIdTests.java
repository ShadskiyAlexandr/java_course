package com.quantori;

import com.quantori.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

@Epic(value = "Delete passenger")
public class DeletePassengerByIdTests extends BaseTest {

    @BeforeEach
    void createTestData(TestInfo info) {
        if (checkIfTestPositive(info)) {
            createNewPassenger();
        }
    }

    @Test
    @Tag("PositiveTest")
    @DisplayName("Delete passenger by Id")
    @Description(value = "Delete passenger by valid Id - Positive test")
    void deletePassengerByIdTest() {
        testServiceApi
                .deletePassengerById(passengerInfo.getId())
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("NegativeTest")
    @Disabled
    @DisplayName("Delete passenger by invalid Id")
    @Description(value = "Delete passenger by invalid Id - Negative test")
    void deletePassengerByWrongIdTest() {
        testServiceApi
                .deletePassengerById(faker.random().hex(32))
                .then()
                .statusCode(204);
    }
}
