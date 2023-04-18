package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.dto.response.PassengerDetailsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

@Epic(value = "Create passenger")
public class CreatePassengerTests extends BaseTest {

    @AfterEach
    void deleteTestData(TestInfo info) {
        if (checkIfTestPositive(info)) {
            deletePassengerById();
        }
    }

    @Test
    @Tag("PositiveTest")
    @DisplayName("Create passenger with valid data")
    @Description(value = "Create passenger - Positive test")
    void createPassengerTest() {
        passengerInfo =
                testServiceApi
                        .createPassenger(passenger)
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response()
                        .as(PassengerDetailsResponse.class);
    }

    @Test
    @Tag("NegativeTest")
    @DisplayName("Create passenger with null body")
    @Description(value = "Create passenger with null body - Negative test")
    void createPassengerWithoutBodyTest() {
        testServiceApi
                .createPassenger(null)
                .then()
                .statusCode(400);
    }

    @Test
    @Tag("NegativeTest")
    @DisplayName("Create passenger without name")
    @Description(value = "Create passenger with empty name field - Negative test")
    void createPassengerWithoutNameTest() {
        passenger = new PassengerDetailsRequest()
                .withTrips(faker.random().nextInt(100, 200))
                .withAirline(faker.random().nextInt(1, 10));

        testServiceApi
                .createPassenger(null)
                .then()
                .statusCode(400);
    }

    @Test
    @Tag("NegativeTest")
    @DisplayName("Create passenger without trips")
    @Description(value = "Create passenger with empty trips field - Negative test")
    void createPassengerWithoutTripsTest() {
        passenger = new PassengerDetailsRequest()
                .withName(faker.name().fullName())
                .withAirline(faker.random().nextInt(1, 10));

        testServiceApi
                .createPassenger(null)
                .then()
                .statusCode(400);
    }
    @Test
    @Tag("NegativeTest")
    @DisplayName("Create passenger without airline")
    @Description(value = "Create passenger with empty airline field - Negative test")
    void createPassengerWithoutAirlineTest() {
        passenger = new PassengerDetailsRequest()
                .withName(faker.name().fullName())
                .withTrips(faker.random().nextInt(100, 200));

        testServiceApi
                .createPassenger(null)
                .then()
                .statusCode(400);
    }
}
