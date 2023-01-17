package com.rest.rest_api.restAPI.controllers;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest_api.mathLogic.FileReaders.FileBuilder;
import com.rest.rest_api.mathLogic.FileReaders.ZippedFileReader;
import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;
import com.rest.rest_api.restAPI.response.FileResponse;

@RestController
public class ControllerZip {
    @GetMapping("/Zipped")
    public ResponseEntity<FileResponse> Zipped(@RequestParam(value = "input") String inputFile,
                                               @RequestParam(value = "output") String outputFile) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            ZippedFileReader.WriteZipped(inputFile, outputFile);
        } catch(Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().build();
        }                                            
        FileResponse response = FileResponse.builder().fileName(outputFile).fileSize(file.getTotalSpace()).downloadUri(outputFile).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/Unzip")
    public ResponseEntity<FileResponse> Unzip(@RequestParam(value = "input") String inputFile,
                                              @RequestParam(value = "output") String outputFile) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            FileBuilder builder = new FileBuilder(inputFile.substring(inputFile.lastIndexOf('.') + 1), inputFile);
            builder.setZip(true);
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
