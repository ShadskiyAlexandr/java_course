package com.quantori;

import com.quantori.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePassengerByIdTests extends BaseTest {

    @BeforeEach
    void createTestData() {
        createNewPassenger();
    }

    @Test
    void deletePassengerByIdTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/{id}", passengerInfo.getId())
                .then()
                .spec(responseSpec);
    }
}
