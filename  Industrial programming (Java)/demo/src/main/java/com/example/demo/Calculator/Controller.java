package com.example.demo.Calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/calculate")
public class Controller 
{
    @GetMapping("add/{firstArgument}/{secondArggument}")
    public ResponseEntity<Double> Add(@PathVariable Double firstArgument, @PathVariable Double secondArggument) 
    {
        var answer = Engine.Add(firstArgument, secondArggument);
        return new ResponseEntity<Double>(answer, HttpStatus.ACCEPTED);
    }

    @GetMapping("multiply/{firstArgument}/{secondArggument}")
    public ResponseEntity<Double> Multiply(@PathVariable Double firstArgument, @PathVariable Double secondArggument) 
    {
        var answer = Engine.Multiply(firstArgument, secondArggument);
        return new ResponseEntity<Double>(answer, HttpStatus.ACCEPTED);
    }

    @GetMapping("minus/{firstArgument}/{secondArggument}")
    public ResponseEntity<Double> Minus(@PathVariable Double firstArgument, @PathVariable Double secondArggument) 
    {
        var answer = Engine.Sub(firstArgument, secondArggument);
        return new ResponseEntity<Double>(answer, HttpStatus.ACCEPTED);
    }

    @GetMapping("divide/{firstArgument}/{secondArggument}")
    public ResponseEntity<Double> Divide(@PathVariable Double firstArgument, @PathVariable Double secondArggument) 
    {
        double answer;
        try 
        {
            answer = Engine.Divide(firstArgument, secondArggument);
        } catch(IllegalArgumentException ex) 
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Double>(answer, HttpStatus.ACCEPTED);
    }
}