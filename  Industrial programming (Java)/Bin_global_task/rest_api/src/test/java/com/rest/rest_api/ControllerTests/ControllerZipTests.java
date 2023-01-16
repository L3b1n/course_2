package com.rest.rest_api.ControllerTests;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

import static com.rest.rest_api.testsEnum.TestConstants.*;

public class ControllerZipTests {
    private static final String Calculate_URL = "/Zipped";

    @AfterAll
    public void deleteFiles() {
        File json = new File(OUTPUT_TXT);
        File txt = new File(OUTPUT_TXT);
        File xml = new File(OUTPUT_TXT);
        File zip = new File(OUTPUT_ZIP);
        json.deleteOnExit();
        txt.deleteOnExit();
        xml.deleteOnExit();
        zip.deleteOnExit();
    }

    @Test
    public void TXTFileCalculate() {
        given().queryParam("input", DEFAULT_TXT)
               .queryParam("output", OUTPUT_TXT)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }
}