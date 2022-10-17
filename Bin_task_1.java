import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;

class Simple_task
{
    Simple_task(double _x, int _n)
    {
        x = _x; n = _n; e = Math.pow(10, -n);
    }

    double Simple_degree()
    {
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

class Complex_task
{
    Complex_task(double _x, int _n)
    {
        n = BigInteger.valueOf(_n);
        x = BigDecimal.valueOf(_x);
        e = BigDecimal.valueOf(Math.pow(10, -_n));
    }

    BigDecimal Complex_degree()
    {
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



class Bin_task_1
{
    public static void main(String[] args) throws IOException
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