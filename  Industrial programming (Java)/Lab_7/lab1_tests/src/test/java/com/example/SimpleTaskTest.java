package com.example;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleTaskTest {
    @Test
    public void SimpleTest1() throws Exception {
        Simple_task object = new Simple_task(0.15, 15);
        assertEquals(1 / (Math.pow((0.15 + 1), 3)), object.Simple_degree(),  1e-10);
    }

    @Test
    public void SimpleTest2() throws Exception {
        Simple_task object = new Simple_task(0.39, 50);
        assertEquals(1 / (Math.pow((0.39 + 1), 3)), object.Simple_degree(),  1e-10);
    }

    @Test
    public void SimpleTest3() throws Exception {
        Simple_task object = new Simple_task(10, 2);
        Exception exeption = (Exception) assertThrows(Exception.class, () -> { object.Simple_degree();});

        String expectedMes = "Error! X mast be from range [-1, 1]";
        String actualMes = exeption.getMessage();

        assertEquals(expectedMes, actualMes);
    }

    @Test
    public void SimpleTest4() throws Exception {
        Simple_task object = new Simple_task(-5, 100);
        Exception exeption = (Exception) assertThrows(Exception.class, () -> { object.Simple_degree();});

        String expectedMes = "Error! X mast be from range [-1, 1]";
        String actualMes = exeption.getMessage();

        assertEquals(expectedMes, actualMes);
    }
}
