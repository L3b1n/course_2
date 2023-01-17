package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.example.demo.Calculator.Facade;

import java.io.IOException;

class CalculationFacadeTest 
{
    @Test
    void add() throws IOException 
    {
        var facade = new Facade();
        double expectedResult = 12.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = facade.Add(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void multiply() throws IOException 
    {
        var facade = new Facade();
        double expectedResult = 35.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = facade.Multiply(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void misubnus() throws IOException 
    {
        var facade = new Facade();
        double expectedResult = -2;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = facade.Sub(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void divide() throws IOException 
    {
        var facade = new Facade();
        double expectedResult = 5.0 / 7.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = facade.Divide(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void divide_throwsIllegalArgument() throws IOException
    {
        var facade = new Facade();
        double arg1 = 5.0;
        double arg2 = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> facade.Divide(arg1, arg2));
    }
}
