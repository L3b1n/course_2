package com.rest.rest_api.ControllerTests;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

import static com.rest.rest_api.testsEnum.TestConstants.*;

public class ControllerCalculationTests extends StandartControllerTests {
    private static final String Calculate_URL = "/Calculate";

    @AfterAll
    public void deleteFiles() {
        File json = new File(OUTPUT_JSON);
        File txt = new File(OUTPUT_TXT);
        File xml = new File(OUTPUT_XML);
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

    @Test
    public void XMLFileCalculate() {
        given().queryParam("input", DEFAULT_XML)
               .queryParam("output", OUTPUT_XML)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_XML).exists(), "File " + OUTPUT_XML + " didn't uploaded.");
    }

    @Test
    public void JSONFileCalculate() {
        given().queryParam("input", DEFAULT_JSON)
               .queryParam("output", OUTPUT_JSON)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_JSON).exists(), "File " + OUTPUT_JSON + " didn't uploaded.");
    }

    @Test
    public void ZipFileCalculate() {
        given().queryParam("input", DEFAULT_ZIP)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("iszipped", true)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", false)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }

    @Test
    public void EncryptedTXTFileCalculate() {
        given().queryParam("input", ENCRYPTED_TXT)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }

    @Test
    public void EncryptedXMLFileCalculate() {
        given().queryParam("input", ENCRYPTED_XML)
               .queryParam("output", OUTPUT_XML)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_XML).exists(), "File " + OUTPUT_XML + " didn't uploaded.");
    }

    @Test
    public void EncryptedJSONFileCalculate() {
        given().queryParam("input", ENCRYPTED_JSON)
               .queryParam("output", OUTPUT_JSON)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_JSON).exists(), "File " + OUTPUT_JSON + " didn't uploaded.");
    }

    @Test
    public void CompressedTXTFileCalculate() {
        given().queryParam("input", COMPRESSED_TXT)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }

    @Test
    public void CompressedXMLFileCalculate() {
        given().queryParam("input", COMPRESSED_XML)
               .queryParam("output", OUTPUT_XML)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_XML).exists(), "File " + OUTPUT_XML + " didn't uploaded.");
    }

    @Test
    public void CompressedJSONFileCalculate() {
        given().queryParam("input", COMPRESSED_JSON)
               .queryParam("output", OUTPUT_JSON)
               .queryParam("iszipped", false)
               .queryParam("iscompressed", false)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Calculate_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_JSON).exists(), "File " + OUTPUT_JSON + " didn't uploaded.");
    }
}