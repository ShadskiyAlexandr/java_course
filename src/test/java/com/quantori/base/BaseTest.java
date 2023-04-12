package com.quantori.base;

import com.github.javafaker.Faker;
import com.quantori.dto.request.PassengerInfoRequest;
import com.quantori.dto.response.PassengerInfoResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

abstract public class BaseTest {
    protected static Properties props = new Properties();
    protected static Map<String, String> headers = new HashMap<>();
    protected static ResponseSpecification responseSpec;
    protected static RequestSpecification requestSpec;
    protected PassengerInfoRequest passenger;

    protected PassengerInfoResponse passengerInfo;

    protected static Faker faker;

    @BeforeAll
    static void beforeAll() {
        loadProperties();
        RestAssured.baseURI = props.getProperty("base.url");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        faker = new Faker();

        requestSpec = new RequestSpecBuilder()
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    @BeforeEach
    void setUp() {
        passenger = new PassengerInfoRequest()
                .withName(faker.name().fullName())
                .withTrips(faker.random().nextInt(100, 200))
                .withAirline(faker.random().nextInt(1, 10));
    }

    protected void deletePassengerById() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/{id}", passengerInfo.getId())
                .then()
                .spec(responseSpec);
    }

    protected void createNewPassenger() {
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

    private static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/test/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
