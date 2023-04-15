package com.quantori.base;

import com.github.javafaker.Faker;
import com.quantori.dto.request.PassengerDetailsRequest;
import com.quantori.dto.response.PassengerDetailsResponse;
import com.quantori.service.TestServiceApi;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

abstract public class BaseTest {
    protected static ResponseSpecification responseSpec;
    protected PassengerDetailsRequest passenger;
    protected static TestServiceApi testServiceApi;

    protected PassengerDetailsResponse passengerInfo;

    protected static Faker faker;

    @BeforeAll
    static void beforeAll() {

        testServiceApi = new TestServiceApi();

        faker = new Faker();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    @BeforeEach
    void setUp() {
        passenger = new PassengerDetailsRequest()
                .withName(faker.name().fullName())
                .withTrips(faker.random().nextInt(100, 200))
                .withAirline(faker.random().nextInt(1, 10));
    }

    protected void deletePassengerById() {
        testServiceApi
                .deletePassengerById(passengerInfo.getId())
                .then()
                .spec(responseSpec);
    }

    protected void createNewPassenger() {
        passengerInfo =
                testServiceApi
                        .createPassenger(passenger)
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response()
                        .as(PassengerDetailsResponse.class);
    }
}
