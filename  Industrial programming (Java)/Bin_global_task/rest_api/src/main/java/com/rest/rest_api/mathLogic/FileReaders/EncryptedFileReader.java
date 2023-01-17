package com.rest.rest_api.mathLogic.FileReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;

public class EncryptedFileReader extends FileReader {
    protected String key;

    public EncryptedFileReader(String key, InterfaceFileReader fileReader) {
        super(fileReader);
        this.key = key;
    }

    public static void Encrypt(String key, String inputFileName, String outputFileName) throws Exception {
        try {
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
        } catch(Exception e) {
            throw new Exception("Error in file encode. Check selected file, actions and try again.", e);
        }
    }

    public byte[] Decrypt(String key, InputStream is) throws Exception {
        try {
            Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] inputBytes = is.readAllBytes();
            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
            return outputBytes;
        } catch(Exception e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }
    
    private byte[] Decrypt(String key, byte[] tempByte) throws Exception {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(tempByte));
            return outputBytes;
        } catch(Exception e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.Write(result, outputFileName);
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.WriteResult(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception {
        try {
            FileInputStream Reader = new FileInputStream(inputName);
            byte[] readFile = Decrypt(key, Reader);
            return reader.Transform(readFile);
        } catch(Throwable e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> ReadResult() throws Exception {
        try {
            FileInputStream Reader = new FileInputStream(inputName);
            byte[] readFile = Decrypt(key, Reader);
            return reader.TransformResult(readFile);
        } catch(Throwable e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception {
        try {
            byte[] decryptByte = Decrypt(key, tempByte);
            return reader.Transform(decryptByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }
    
    @Override
    public ArrayList<ArrayList<String>> TransformResult(byte[] tempByte) throws Exception {
        try {
            byte[] decryptByte = Decrypt(key, tempByte);
            return reader.Transform(decryptByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decode. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception {
        return reader.Calculate(readFile);
    }
    
    @Override
    public void getResult(String outputFileName) throws Exception {
        ArrayList<ArrayList<String>> readFile = ReadResult();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        WriteResult(result, outputFileName);
    }
}
