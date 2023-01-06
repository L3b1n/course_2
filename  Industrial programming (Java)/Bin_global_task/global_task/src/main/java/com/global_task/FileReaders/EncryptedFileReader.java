package com.global_task.FileReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.global_task.contracts.Interface.InterfaceFileReader;

public class EncryptedFileReader extends FileReader {
    protected String key;

    public EncryptedFileReader(String key, InterfaceFileReader fileReader) {
        super(fileReader);
        this.key = key;
    }

    public static void Encrypt(String key, String inputFileName, String outputFileName) throws Throwable {
        FileInputStream is = new FileInputStream(inputFileName);
        FileOutputStream os = new FileOutputStream(outputFileName);
        Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] inputBytes = is.readAllBytes();
        byte[] outputBytes = cipher.doFinal(inputBytes);
        os.write(Base64.getEncoder().encode(outputBytes));
        os.close();
        is.close();
    }

    public byte[] Decrypt(String key, InputStream is) throws Throwable {
        Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] inputBytes = is.readAllBytes();
        byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
        return outputBytes;
    }
    
    private byte[] Decrypt(String key, byte[] tempByte) throws Throwable {
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(tempByte));
        return outputBytes;
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        reader.Write(result, outputFileName);
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        reader.WriteResult(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable {
        FileInputStream Reader = new FileInputStream(inputName);
        byte[] readFile = Decrypt(key, Reader);
        return reader.Transform(readFile);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Throwable {
        byte[] decryptByte = Decrypt(key, tempByte);
        return reader.Transform(decryptByte);
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable {
        return reader.Calculate(readFile);
    }
    
    @Override
    public void getResult(String outputFileName) throws Throwable {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        WriteResult(result, outputFileName);
    }
}
