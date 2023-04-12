package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePassengerByIdTests extends BaseTest {

    @BeforeEach
    void createTestData() {
        passengerInfo =
                given()
                        .spec(requestSpec)
                        .body(passenger)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response()
                        .getBody()
                        .as(PassengerInfoResponse.class);
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
