package com.global_task.java.FileReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.global_task.contracts.Interface.InterfaceFileReader;

public class EncryptedFileReader extends FileReader {
    protected String key;

    public EncryptedFileReader(String key, InterfaceFileReader fileReader) {
        super(fileReader);
        this.key = key;
    }

    public void Encrypt(String key, ArrayList<ArrayList<String>> readFile, InputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, os, readFile);
    }

    public ArrayList<ArrayList<String>> Decrypt(String key, InputStream is) throws Throwable {
        return encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, new ArrayList<>());
    }

    public ArrayList<ArrayList<String>> encryptOrDecrypt(String key, int mode, InputStream is, ArrayList<ArrayList<String>> tmp) throws Throwable {
        Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        if(mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] inputBytes = is.readAllBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream("encrypted.txt");
            outputStream.write(Base64.getEncoder().encode(outputBytes));
            outputStream.close();
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] inputBytes = is.readAllBytes();
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
            String tempString = new String(outputBytes, StandardCharsets.UTF_8);
            StringTokenizer stringTokenizer = new StringTokenizer(tempString, "\n");
            while(stringTokenizer.hasMoreTokens()) {
                ArrayList<String> temp = new ArrayList<>(); 
                temp.add(stringTokenizer.nextToken());
                readFile.add(temp);
            }
            return readFile;
        }
        return tmp;
    }

    public ArrayList<ArrayList<String>> Decrypt(String key, ArrayList<ArrayList<String>> readFile) throws Throwable {
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] inputBytes = readFile.get(0).get(0).toString().getBytes();
        byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
        String tempString = new String(outputBytes, StandardCharsets.UTF_8);
        StringTokenizer stringTokenizer = new StringTokenizer(tempString, "\n");
        ArrayList<ArrayList<String>> decryptFile = new ArrayList<>();
        while(stringTokenizer.hasMoreTokens()) {
            ArrayList<String> temp = new ArrayList<>(); 
            temp.add(stringTokenizer.nextToken());
            decryptFile.add(temp);
        }
        return decryptFile;
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        reader.Write(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable {
        FileInputStream Reader = new FileInputStream(inputName);
        ArrayList<ArrayList<String>> readFile = Decrypt(key, Reader);
        return reader.Transform(readFile);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(ArrayList<ArrayList<String>> readFile) throws Throwable {
        ArrayList<ArrayList<String>> decryptArray = Decrypt(key, readFile);
        return reader.Transform(decryptArray);
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable {
        return reader.Calculate(readFile);
    }
    
    @Override
    public void getResult(String outputFileName) throws Throwable {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }

}
