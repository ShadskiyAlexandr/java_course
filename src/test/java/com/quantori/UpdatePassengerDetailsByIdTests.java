package com.quantori;

import com.quantori.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
        given()
                .spec(requestSpec)
                .body(passenger)
                .when()
                .put("/{id}", passengerInfo.getId())
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }
}

