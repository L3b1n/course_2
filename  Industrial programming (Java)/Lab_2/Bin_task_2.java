import java.util.Formatter;
import java.util.Scanner;
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



class Bin_task_2 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println(new String("Enter the number x from -1 to 1 and pow k"));

        double x = input.nextDouble();
        if(x >= 1 || x <= -1)
        {
            System.out.println("The number doesn't belong the interval");
            System.exit(0);
        }
        int n = input.nextInt();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(n + 1);

        Simple_task model = new Simple_task(x, n);
        System.out.println("Standart:   " + formatter.format(1 / (Math.pow((x + 1), 3))));
        System.out.println("Simple_my:   " + formatter.format(model.Simple_degree()));

        double double_result = model.Simple_degree() * 100;
        int int_result = (int)Math.abs(model.Simple_degree() * 100);
        Formatter first = new Formatter();
        first.format("1) OCT = %o, HEX = %x", int_result, int_result);
        System.out.println("\nInt number:   " + int_result);
        System.out.println("Double number:   " + double_result);
        System.out.println(first);

        Formatter second = new Formatter();
        second.format("2) Positive = %f, Negative = %f", double_result, -double_result);
        System.out.println(second);

        Formatter third = new Formatter();
        third.format("3) |%f|, |%12f|, |%012f|", double_result, double_result, double_result);
        System.out.println(third);

        Formatter fourth = new Formatter();
        fourth.format("4) 1 decimal place: %.1f%n   5 decimal place: %.5f", double_result, double_result);
        System.out.println(fourth);

        Formatter fifth = new Formatter();
        fifth.format("5) +: %+d%n% d%n% d", int_result, int_result, -int_result);
        System.out.println(fifth);
        Formatter fifth_1 = new Formatter();
        fifth_1.format("   ,: %,.2f", double_result * 10000);
        System.out.println(fifth_1);
        Formatter fifth_2 = new Formatter();
        fifth_2.format("   (: % (d", -int_result);
        System.out.println(fifth_2);

        Formatter sixth = new Formatter();
        sixth.format("6) %5$d %1$d %4$d %2$d %3$d", 10, 20, 30, 40, 50);
        System.out.println(sixth);
        input.close();
    }
}