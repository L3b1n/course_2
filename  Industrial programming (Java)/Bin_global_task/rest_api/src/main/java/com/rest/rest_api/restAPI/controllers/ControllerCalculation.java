package com.rest.rest_api.restAPI.controllers;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest_api.restAPI.response.FileResponse;
import com.rest.rest_api.mathLogic.FileReaders.FileBuilder;
import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;

@RestController
public class ControllerCalculation {
    @GetMapping("/Calculate")
    public ResponseEntity<FileResponse> Calculate(@RequestParam(value = "input") String inputFile,
                                          @RequestParam(value = "output") String outputFile,
                                          @RequestParam(value = "iszipped", required = false) boolean isZipped,
                                          @RequestParam(value = "iscompressed", required = false) boolean isCompressed,
                                          @RequestParam(value="decryptkey", required = false) String key) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            System.out.println(isZipped);
            System.out.println(isCompressed);
            System.out.println(key);
            FileBuilder builder = new FileBuilder(inputFile.substring(inputFile.lastIndexOf('.') + 1), inputFile);
            builder.setZip(isZipped);
            if(key != null && !key.equals("false")) {
                builder.setEncrypt(key);
            }
            builder.setCompressed(isCompressed);
            InterfaceFileReader reader = builder.getFileReader();
            reader.getResult(outputFile);
        } catch(Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().build();
        }
        FileResponse response = FileResponse.builder().fileName(outputFile).fileSize(file.getTotalSpace()).downloadUri("/download/" + outputFile).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
