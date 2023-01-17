package com.rest.rest_api.restAPI.controllers;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest_api.mathLogic.FileReaders.CompressFileReader;
import com.rest.rest_api.mathLogic.FileReaders.FileBuilder;
import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;
import com.rest.rest_api.restAPI.response.FileResponse;

@RestController
public class ControllerCompress {
    @GetMapping("/Compress")
    public ResponseEntity<FileResponse> Compress(@RequestParam(value = "input") String inputFile,
                                                 @RequestParam(value = "output") String outputFile,
                                                 @RequestParam(value = "level") Integer compressLevel) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            CompressFileReader.Compress(compressLevel, inputFile, outputFile);
        } catch(Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().build();
        }                                            
        FileResponse response = FileResponse.builder().fileName(outputFile).fileSize(file.getTotalSpace()).downloadUri(outputFile).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/Decompress")
    public ResponseEntity<FileResponse> Decompress(@RequestParam(value = "input") String inputFile,
                                                   @RequestParam(value = "output") String outputFile) {
        File file = new File(outputFile);
        try {
            System.out.println(inputFile);
            System.out.println(outputFile);
            FileBuilder builder = new FileBuilder(inputFile.substring(inputFile.lastIndexOf('.') + 1), inputFile);
            builder.setCompressed(true);
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
