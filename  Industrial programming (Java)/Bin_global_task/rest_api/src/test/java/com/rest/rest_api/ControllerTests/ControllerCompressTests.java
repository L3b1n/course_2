package com.rest.rest_api.ControllerTests;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

import static com.rest.rest_api.testsEnum.TestConstants.*;

public class ControllerCompressTests extends StandartControllerTests {
    private static final String Compress_URL = "/Compress";
    private static final String Decompress_URL = "/Decompress";

    @AfterAll
    public void deleteFiles() {
        File txt = new File(OUTPUT_TXT);
        txt.deleteOnExit();
    }

    @Test
    public void FileCompress() {
        given().queryParam("input", DEFAULT_TXT)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("level", 4)
               .log().all().when().post(Compress_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }

    @Test
    public void FileDecompress() {
        given().queryParam("input", COMPRESSED_TXT)
               .queryParam("output", OUTPUT_TXT)
               .log().all().when().post(Decompress_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }
}