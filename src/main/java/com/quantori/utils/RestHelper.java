package com.quantori.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestHelper {

    private static Properties props = new Properties();
    private static Map<String, String> headers = new HashMap<>();
    private static RequestSpecification requestSpec;

    public Response get(String path) {
        return get(path, null);
    }

    public Response get(String path, Map<String, ?> params) {
        setUpSpecification(params);
        return requestSpec.get(path);
    }

    public Response post(Object body) {
        return post(null, null, body);
    }

    public Response post(String path, Map<String, ?> params, Object body) {
        setUpSpecification(params, body);
        if (path == null) {
            return requestSpec.post();
        } else {
            return requestSpec.post(path);
        }
    }

    public Response delete(String path) {
        return delete(path, null);
    }

    public Response delete(String path, Map<String, ?> params) {
        setUpSpecification(params);
        return requestSpec.delete(path);
    }

    public Response put(String path, Object body) {
        return put(path, null, body);
    }

    public Response put(String path, Map<String, ?> params, Object body) {
        setUpSpecification(params, body);
        return requestSpec.put(path);
    }

    private void setUpSpecification(Map<String, ?> params) {
        setUpSpecification(params, null);
    }

    private void setUpSpecification(Map<String, ?> params, Object body) {
        requestSpec = buildRequestSpecification();
        if (params != null) {
            requestSpec.queryParams(params);
        }
        if (body != null) {
            requestSpec.body(body);
        }
    }

    private RequestSpecification buildRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);
        if (headers != null) {
            requestSpecBuilder
                    .addHeaders(headers);
        }

        return buildRequestSpecification(requestSpecBuilder);
    }

    private RequestSpecification buildRequestSpecification(RequestSpecBuilder requestSpecBuilder) {
        loadProperties();
        String baseUrl = props.getProperty("base.url");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return given
                (requestSpecBuilder
                        .setBaseUri(baseUrl)
                        .log(LogDetail.METHOD)
                        .log(LogDetail.URI)
                        .build()
                );
    }

    private static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/test/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
