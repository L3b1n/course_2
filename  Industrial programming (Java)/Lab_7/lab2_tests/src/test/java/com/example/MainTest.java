package com.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest 
{
    @Test
    public void FirstTest() {
        int n = 10;
        Double[][] matrix = new Double[n][n];
        Double[][] matrix1 = new Double[n][n];
        Double[][] matrix2 = new Double[n][n];
        Double[][] matrix_final= new Double[n][n];
        Main.definition(matrix1, n);
        Main.definition(matrix2, n);
        Main.multiply(matrix_final, matrix1, matrix2, n);

        double m;
        for(int i = 0; i < n; i++) 
        {
            m = 1;
            for(int j = 0; j < n; j++){ m *= matrix2[i][j];}
            System.out.println("Произведение " + i + " = " + m);
            for(int j = 0; j < n; j++){ matrix[j][i] = matrix1[j][i] + m;}
        }

        for(int i = 0; i < n; i++) 
        {
            for(int j = 0; j < n; j++){ assertEquals(matrix[i][j], matrix_final[i][j]);}
        }
    }
    
    @Test
    public void SecondTest() {
        int n = 100;
        Double[][] matrix = new Double[n][n];
        Double[][] matrix1 = new Double[n][n];
        Double[][] matrix2 = new Double[n][n];
        Double[][] matrix_final= new Double[n][n];
        Main.definition(matrix1, n);
        Main.definition(matrix2, n);
        Main.multiply(matrix_final, matrix1, matrix2, n);

        double m;
        for(int i = 0; i < n; i++) 
        {
            m = 1;
            for(int j = 0; j < n; j++){ m *= matrix2[i][j];}
            System.out.println("Произведение " + i + " = " + m);
            for(int j = 0; j < n; j++){ matrix[j][i] = matrix1[j][i] + m;}
        }

        for(int i = 0; i < n; i++) 
        {
            for(int j = 0; j < n; j++){ assertEquals(matrix[i][j], matrix_final[i][j]);}
        }
    }

    @Test
    public void ThirdTest() {
        int n = 999;
        Double[][] matrix = new Double[n][n];
        Double[][] matrix1 = new Double[n][n];
        Double[][] matrix2 = new Double[n][n];
        Double[][] matrix_final= new Double[n][n];
        Main.definition(matrix1, n);
        Main.definition(matrix2, n);
        Main.multiply(matrix_final, matrix1, matrix2, n);

        double m;
        for(int i = 0; i < n; i++) 
        {
            m = 1;
            for(int j = 0; j < n; j++){ m *= matrix2[i][j];}
            System.out.println("Произведение " + i + " = " + m);
            for(int j = 0; j < n; j++){ matrix[j][i] = matrix1[j][i] + m;}
        }

        for(int i = 0; i < n; i++) 
        {
            for(int j = 0; j < n; j++){ assertEquals(matrix[i][j], matrix_final[i][j]);}
        }
    }
}
