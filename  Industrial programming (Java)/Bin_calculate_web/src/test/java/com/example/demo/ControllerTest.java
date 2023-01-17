package com.example.demo;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

class CalculationControllerTest 
{
    @BeforeAll
    public static void setUp() 
    {
        baseURI = "http://localhost/api/calculate/";
        port = 8080;
    }

    @Test
    void addTest() 
    {
        double a = 7;
        double b = 14;
        double result = 21;
        Response response = given().pathParam("firtsArgument", a)
                                   .pathParam("secondArggument", b)
                                   .expect().statusCode(200)
                                   .when().post("add/{firtsArgument}/{secondArggument}");
        double actualResult = response.then().extract().body().htmlPath().getDouble("body");
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    void multiplyTest() 
    {
        double a = 7;
        double b = 14;
        double result = 98;
        Response response = given().pathParam("firtsArgument", a)
                                   .pathParam("secondArggument", b)
                                   .expect().statusCode(200)
                                   .when().post("multiply/{firtsArgument}/{secondArggument}");
        double actualResult = response.then().extract().body().htmlPath().getDouble("body");
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    void minusTest() 
    {
        double a = 34;
        double b = 14;
        double result = 20;
        Response response = given().pathParam("firtsArgument", a)
                                   .pathParam("secondArggument", b)
                                   .expect().statusCode(200)
                                   .when().post("minus/{firtsArgument}/{secondArggument}");
        double actualResult = response.then().extract().body().htmlPath().getDouble("body");
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    void divideTest() 
    {
        double a = 7;
        double b = 14;
        double result = 0.5;
        Response response = given().pathParam("firtsArgument", a)
                                   .pathParam("secondArggument", b)
                                   .expect().statusCode(200)
                                   .when().post("divide/{firtsArgument}/{secondArggument}");
        double actualResult = response.then().extract().body().htmlPath().getDouble("body");
        Assertions.assertEquals(result, actualResult);
    }
}
