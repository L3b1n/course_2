package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.example.demo.Calculator.Engine;

class CalculationEngineTest 
{
    @Test
    void add() 
    {
        double expectedResult = 12.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = Engine.Add(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void sub() 
    {
        double expectedResult = -2;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = Engine.Sub(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void multiply() 
    {
        double expectedResult = 35.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = Engine.Multiply(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void divide() 
    {
        double expectedResult = 5.0 / 7.0;
        double arg1 = 5.0;
        double arg2 = 7.0;
        var result = Engine.Divide(arg1, arg2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void divide_throwsIllegalArgument() 
    {
        double arg1 = 5.0;
        double arg2 = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Engine.Divide(arg1, arg2));
    }
}