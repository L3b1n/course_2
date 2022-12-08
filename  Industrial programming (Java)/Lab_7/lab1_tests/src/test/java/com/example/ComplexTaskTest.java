package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class ComplexTaskTest 
{
    @Test
    public void ComplexTest1() throws Exception 
    {
        Complex_task object = new Complex_task(0.15, 15);
        BigDecimal temp = BigDecimal.valueOf(1 / (Math.pow((0.15 + 1), 3)));
        assertEquals(temp.setScale(10, RoundingMode.DOWN), object.Complex_degree().setScale(10, RoundingMode.DOWN));
    }

    @Test
    public void ComplexTest2() throws Exception  
    {
        Complex_task object = new Complex_task(0.39, 50);
        BigDecimal temp = BigDecimal.valueOf(1 / (Math.pow((0.39 + 1), 3)));
        assertEquals(temp.setScale(10, RoundingMode.DOWN), object.Complex_degree().setScale(10, RoundingMode.DOWN));
    }

    @Test
    public void ComplexTest3() throws Exception 
    {
        Complex_task object = new Complex_task(10, 2);
        Exception exeption = (Exception) assertThrows(Exception.class, () -> { object.Complex_degree(); });

        String expectedMes ="Error! X mast be from range [-1, 1]";
        String actualMes = exeption.getMessage();
        assertEquals(expectedMes, actualMes);
    }

    @Test
    public void ComplexTest4() throws Exception 
    {
        Complex_task object = new Complex_task(-5, 100);
        final Exception exeption = (Exception) assertThrows(Exception.class, () -> { object.Complex_degree(); });

        String expectedMes = "Error! X mast be from range [-1, 1]";
        String actualMes = exeption.getMessage();
        assertEquals(expectedMes, actualMes);
    }
}
