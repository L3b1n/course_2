package com.global_task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryted {
    public static void main(String[] args) {
        // try {
        //     String key = "squirreldgwndlaj";
        //     encrypt(key, "src/main/tests/com/global_task/input.txt", "encrypted.txt");
        // } catch (Throwable e) {
        //     e.printStackTrace();
        // }
        System.out.println("EeZuZzZuZzEdCc".matches("([acdeCE]*Zu[acdeCE]*Zz[acdeCE]*)*?"));
        System.out.println("EeZuZzZzEdCc".matches("([aedcEC]*Zu[aedcEC]*)*?([aedcEC]*Zz[aedcEC]*)*?"));
    }
    
    public static void encrypt(String key, String inputFileName, String outputFileName) throws Throwable {
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);
        Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = inputStream.readAllBytes();

        byte[] outputBytes;
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        outputBytes = cipher.doFinal(inputBytes);
        outputStream.write(Base64.getEncoder().encode(outputBytes));
        inputStream.close();
        outputStream.close();
    }
}
