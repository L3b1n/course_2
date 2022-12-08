package com.example;

public class Simple_task
{
    Simple_task(double _x, int _n)
    {
        x = _x; n = _n; e = Math.pow(10, -n);
    }

    double Simple_degree() throws Exception
    {
        if(x < -1 || x > 1) 
        {
            throw new Exception("Error! X mast be from range [-1, 1]");
        }
        double result = 0.0;
        double i = 0.0;
        boolean condition = true;
        while(condition) 
        {
            double temp = Math.pow(-1, i) * Math.pow(x, i) * (i + 1) * (i + 2) / 2;
            if(Math.abs(temp) > e)
            {
                result += temp;
                i++;
            }
            else
            {
                condition = false;
            }
        }
        return result;
    }

    int n;
    double x, e;
}
