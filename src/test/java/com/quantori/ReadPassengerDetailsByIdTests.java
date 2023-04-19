package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.response.PassengerDetailsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic(value = "Get passenger")
public class ReadPassengerDetailsByIdTests extends BaseTest {

    @BeforeEach
    void createTestData(TestInfo info) {
        if (checkIfTestPositive(info)) {
            createNewPassenger();
        }
    }

    @AfterEach
    void deleteTestData(TestInfo info) {
        if (checkIfTestPositive(info)) {
            deletePassengerById();
        }
    }

    @Test
    @Tag("PositiveTest")
    @DisplayName("Get passenger by Id")
    @Description(value = "Test status code for GET request - Positive test")
    void getPassengerById() {
        testServiceApi
                .getPassengerById(passengerInfo.getId())
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }

    @Test
    @Tag("NegativeTest")
    @DisplayName("Get passenger by invalid Id")
    @Description(value = "Test status code for GET request - Negative test")
    void getPassengerByWrongId() {
        testServiceApi
                .getPassengerById(faker.random().hex(32))
                .prettyPeek()
                .then()
                .statusCode(204);
    }

    @Test
    @Tag("PositiveTest")
    @DisplayName("Test passenger details by Id")
    @Description(value = "Test passenger details by Id - Positive test")
    void checkPassengerDetailsById() {
        PassengerDetailsResponse passengerDetailsResponse =
                testServiceApi
                        .getPassengerById(passengerInfo.getId())
                        .prettyPeek()
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .as(PassengerDetailsResponse.class);

        assertAll(
                () -> assertThat(passengerDetailsResponse.getName(), is(equalTo(passenger.getName()))),
                () -> assertThat(passengerDetailsResponse.getAirline().size(), is(equalTo(1))),
                () -> assertThat(passengerDetailsResponse.getAirline().get(0).getId(), is(equalTo(passenger.getAirline()))),
                () -> assertThat(passengerDetailsResponse.getTrips(), is(equalTo(passenger.getTrips())))
        );
    }
}
