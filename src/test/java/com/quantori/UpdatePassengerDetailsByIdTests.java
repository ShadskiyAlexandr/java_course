package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.request.PassengerInfoRequest;
import com.quantori.dto.response.PassengerInfoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdatePassengerDetailsByIdTests extends BaseTest {

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

    @AfterEach
    void deleteTestData() {
        deletePassengerById();
    }

    @Test
    void getDetailsInfoTest() {
        passenger = new PassengerInfoRequest()
                .withName(faker.name().fullName())
                .withTrips(faker.random().nextInt(100, 200))
                .withAirline(faker.random().nextInt(1, 10));

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

