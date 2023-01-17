package com.rest.rest_api.ControllerTests;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

import static com.rest.rest_api.testsEnum.TestConstants.*;

public class ControllerEncryptTests extends StandartControllerTests {
    private static final String Encrypt_URL = "/Encrypt";
    private static final String Decrypt_URL = "/Decrypt";

    @AfterAll
    public void deleteFiles() {
        File txt = new File(OUTPUT_TXT);
        txt.deleteOnExit();
    }

    @Test
    public void FileEncrypt() {
        given().queryParam("input", DEFAULT_TXT)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("encryptkey", KEY)
               .log().all().when().post(Encrypt_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }

    @Test
    public void FileDecrypt() {
        given().queryParam("input", ENCRYPTED_TXT)
               .queryParam("output", OUTPUT_TXT)
               .queryParam("decryptkey", KEY)
               .log().all().when().post(Decrypt_URL)
               .then().statusCode(200);

        Assertions.assertTrue(new File(OUTPUT_TXT).exists(), "File " + OUTPUT_TXT + " didn't uploaded.");
    }
}