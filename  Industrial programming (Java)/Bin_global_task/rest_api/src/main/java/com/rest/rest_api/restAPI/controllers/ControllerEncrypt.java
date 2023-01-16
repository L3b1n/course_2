package com.rest.rest_api.restAPI.controllers;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest_api.restAPI.response.FileResponse;
import com.rest.rest_api.mathLogic.FileReaders.FileBuilder;
import com.rest.rest_api.mathLogic.FileReaders.EncryptedFileReader;
import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;

@RestController
public class ControllerEncrypt {
    @GetMapping("/Encrypt")
    public ResponseEntity<FileResponse> Encrypt(@RequestParam(value = "input") String inputFile,
                                                @RequestParam(value = "output") String outputFile,
                                                @RequestParam(value = "encryptkey") String key) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            System.out.println(key);
            EncryptedFileReader.Encrypt(key, inputFile, outputFile);
        } catch(Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().build();
        }                                            
        FileResponse response = FileResponse.builder().fileName(outputFile).fileSize(file.getTotalSpace()).downloadUri(outputFile).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/Decrypt")
    public ResponseEntity<FileResponse> Decrypt(@RequestParam(value = "input") String inputFile,
                                                @RequestParam(value = "output") String outputFile,
                                                @RequestParam(value = "decryptkey") String key) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            System.out.println(key);
            FileBuilder builder = new FileBuilder(inputFile.substring(inputFile.lastIndexOf('.') + 1), inputFile);
            builder.setEncrypt(key);
            InterfaceFileReader reader = builder.getFileReader();
            reader.Write(reader.Read(), outputFile);
        } catch(Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().build();
        }
        FileResponse response = FileResponse.builder().fileName(outputFile).fileSize(file.getTotalSpace()).downloadUri(outputFile).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}