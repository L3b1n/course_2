package com.example;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Complex_task
{
    Complex_task(double _x, int _n)
    {
        n = BigInteger.valueOf(_n);
        x = BigDecimal.valueOf(_x);
        e = BigDecimal.valueOf(Math.pow(10, -_n));
    }

    BigDecimal Complex_degree() throws Exception
    {
        BigDecimal ex1 = new BigDecimal("1");
        BigDecimal ex2 = new BigDecimal("-1");
        if(x.compareTo(ex1) > 0 || x.compareTo(ex2) < 0) 
        {
            throw new Exception("Error! X mast be from range [-1, 1]");
        }
        BigDecimal result = new BigDecimal(0.0);
        BigDecimal i = new BigDecimal(0.0);
        boolean condition = true;
        while(condition)
        {
            BigDecimal temp = BigDecimal.valueOf(Math.pow(-1, i.intValue()) * Math.pow(x.doubleValue(), i.intValue()) * (i.intValue() + 1) * (i.intValue() + 2) / 2);
            if(temp.abs().compareTo(e) == 1)
            {
                result = result.add(temp);
                i = i.add(BigDecimal.valueOf(1));
            }
            else
            {
                condition = false;
            }
        }
        return result;
    }

    BigInteger n;
    BigDecimal x;
    BigDecimal e;
}