package com.example.demo.Calculator;

import java.io.IOException;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        var facade = new Facade();
        System.out.println(facade.Add(23, 42));
        System.out.println(facade.Multiply(13, 49));
        System.out.println(facade.Divide(10, 134));
        System.out.println(facade.Sub(135, 243));
    }
}