package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerInfoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class CreatePassengerTests extends BaseTest {


    @AfterEach
    void deleteTestData() {
        deletePassengerById();
    }

    @Test
    void createPassengerTest() {
        passengerInfo =
            given()
                    .spec(requestSpec)
                    .body(passenger)
            .when()
                    .post()
                    .prettyPeek()
            .then()
                    .spec(responseSpec)
                    .extract()
                    .response()
                    .as(PassengerInfoResponse.class);
    }
}
