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
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestHelper {

    protected boolean logIn;
    private String baseUrl;

    protected static Properties props = new Properties();
    protected static Map<String, String> headers;
    private static RequestSpecification requestSpec;

    public RestHelper() {
        loadProperties();
    }

    public Response get(String path) {
        return get(path, null);
    }

    public Response get(String path, Map<String, ?> params) {
        setUpSpecification(params);
        return requestSpec.get(path);
    }

    public Response post(String path, Object body) {
        return post(path, null, body);
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

    private void setUpSpecification() {
        setUpSpecification(null, null);
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
        if (headers != null) {
            requestSpecBuilder
                    .addHeaders(headers);
        }
        if (logIn) {
            requestSpecBuilder
                    .setAccept(ContentType.JSON)
                    .setContentType("application/x-www-form-urlencoded");
        } else {
            requestSpecBuilder
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON);
        }

        return buildRequestSpecification(requestSpecBuilder);
    }

    private RequestSpecification buildRequestSpecification(RequestSpecBuilder requestSpecBuilder) {
        if (logIn) {
            baseUrl = props.getProperty("login.url");
            logIn = false;
        } else {
            baseUrl = props.getProperty("base.url");
        }
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return given
                (requestSpecBuilder
                        .setBaseUri(baseUrl)
                        .log(LogDetail.METHOD)
                        .log(LogDetail.URI)
                        .build()
                );
    }

    public Response postUrlEncoded(String path, Map<String, String> formParams) {
        logIn = true;
        setUpSpecification();
        if (formParams != null) {
            requestSpec.formParams(formParams);
        }
        return requestSpec.urlEncodingEnabled(true).post(path);
    }

    protected static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/test/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
