package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(new String("Enter the number x from -1 to 1 and pow k"));
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String line = input.readLine();
            double x = Double.parseDouble(line);
            if(x >= 1 || x <= -1)
            {
                System.out.println("The number doesn't belong the interval");
                System.exit(0);
            }
            line = input.readLine();
            int n = Integer.parseInt(line);
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(n + 1);
            Simple_task simple_model = new Simple_task(x, n);
            System.out.println("Simple_task:");
            System.out.println("Standart: " + formatter.format(1 / (Math.pow((x + 1), 3))));
            System.out.println("Simple_my: " + formatter.format(simple_model.Simple_degree()));

            Complex_task complex_model = new Complex_task(x, n);
            System.out.println("\nComplex_task:");
            System.out.println("Standart: " + formatter.format(1 / (Math.pow((x + 1), 3))));
            System.out.println("Complex_my: " + formatter.format(complex_model.Complex_degree()));
        }
        catch(IOException e)
        {
            System.out.println("Reading error");
        }
    }
}