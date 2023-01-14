package com.example.demo.Calculator;

public class Engine 
{
    public static double Add(double firstArgument, double secondArgument){ return firstArgument + secondArgument;}

    public static double Sub(double firstArgument, double secondArgument){ return firstArgument - secondArgument;}

    public static double Multiply(double firstArgument, double secondArgument){ return firstArgument * secondArgument;}

    public static double Divide(double firstArgument, double secondArgument) 
    {
        if(secondArgument == 0){ throw new IllegalArgumentException("Divide by zero!");}
        return firstArgument / secondArgument;
    }
}