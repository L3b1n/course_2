package com.rest.rest_api.ControllerTests;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

public abstract class StandartControllerTests {
    @BeforeAll
    public static void setUp() {
        baseURI = "http:localhost";
        port = 8080;
    }
}
