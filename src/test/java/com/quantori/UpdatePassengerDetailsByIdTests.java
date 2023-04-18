package com.quantori;

import com.quantori.base.BaseTest;
import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.dto.response.PassengerDetailsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic(value = "Update passenger")
public class UpdatePassengerDetailsByIdTests extends BaseTest {

    private String name;
    private int trips;
    private int airline;

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
    void updateAllFieldsTest() {

        passenger = new PassengerDetailsRequest()
                .withName(faker.name().fullName())
                .withTrips(faker.random().nextInt(100, 200))
                .withAirline(faker.random().nextInt(1, 10));

        testServiceApi
                .updatePassengerById(passengerInfo.getId(), passenger)
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }

    public static Stream<String> fieldNameGenerator() {
        return Stream.of("name", "trips", "airline");
    }

    @ParameterizedTest
    @MethodSource("fieldNameGenerator")
    @DisplayName("Update field")
    @Description(value = "Update passenger field")
    public void updateFieldTest(String fieldName) {
        testServiceApi
                .updatePassengerById(passengerInfo.getId(), updateField(passenger, fieldName))
                .prettyPeek()
                .then()
                .spec(responseSpec);

        PassengerDetailsResponse passengerResponse =
                testServiceApi
                        .getPassengerById(passengerInfo.getId())
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .as(PassengerDetailsResponse.class);

        assertThat("Expected value - " + name,
                checkValueField(passengerResponse, fieldName), is(true));
    }

    private PassengerDetailsRequest updateField(PassengerDetailsRequest passenger, String fieldName) {
        switch (fieldName) {
            case "name":
                this.name = faker.name().fullName();
                passenger.name = name;
                break;
            case "trips":
                this.trips = faker.random().nextInt(100, 200);
                passenger.trips = trips;
                break;
            case "airline":
                this.airline = faker.random().nextInt(1, 10);
                passenger.airline = airline;
                break;
        }
        return passenger;
    }

    private boolean checkValueField(PassengerDetailsResponse passenger, String fieldName) {
        switch (fieldName) {
            case "name":
                System.out.println("actual - " + passenger.getName());
                System.out.println("expected - " + this.name);
                return passenger.getName().equals(this.name);
            case "trips":
                System.out.println("actual - " + passenger.getTrips());
                System.out.println("expected - " + this.trips);
                return passenger.getTrips() == this.trips;
            case "airline":
                System.out.println("actual - " + passenger.getAirline().get(0).getId());
                System.out.println("expected - " + this.airline);
                return passenger.getAirline().get(0).getId() == airline;
            default:
                return false;
        }
    }

}

