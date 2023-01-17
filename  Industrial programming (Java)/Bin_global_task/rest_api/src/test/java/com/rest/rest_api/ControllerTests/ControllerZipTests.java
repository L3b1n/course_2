package com.rest.rest_api.ControllerTests;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

import static com.rest.rest_api.testsEnum.TestConstants.*;

public class ControllerZipTests extends StandartControllerTests {
    private static final String Zipped_URL = "/Zipped";
    private static final String Unzip_URL = "/Unzip";

    @AfterAll
    public void deleteFiles() {
        File txt = new File(OUTPUT_TXT);
        File zip = new File(OUTPUT_ZIP);
        txt.deleteOnExit();
        zip.deleteOnExit();
    }

    @Test
    public void FileZipped() {
        given().queryParam("input", DEFAULT_TXT)
               .queryParam("output", OUTPUT_ZIP)
               .log().all().when().post(Zipped_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_ZIP).exists(), "File " + OUTPUT_ZIP + " didn't uploaded.");
    }

    @Test
    public void FileUnzip() {
        given().queryParam("input", DEFAULT_ZIP)
               .queryParam("output", OUTPUT_TXT)
               .log().all().when().post(Unzip_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }
}