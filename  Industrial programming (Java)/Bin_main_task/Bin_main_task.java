import java.io.*;
import java.util.*;

public class Bin_main_task 
{
    static int REGS = 4;
    static String regs_names = new String();

    public static String[] Reader(File xmlFile) throws Exception
    {
        int i = 0;
        FileReader input = new FileReader(xmlFile);
        String[] temp = new String[256];
        Scanner scan = new Scanner(input);
        while(scan.hasNextLine()) 
        {
            temp[i++] = scan.nextLine().toLowerCase();
        }
        input.close();
        return temp;
    }

    public static void Writer(String temp, File xmlFile) throws Exception
    {
        FileWriter output = new FileWriter(xmlFile);
        output.write(temp, 0, temp.length());
        output.close();
    }

    public static int[][] Identity()
    {
        int [][]matrix = new int[REGS + 1][REGS + 1];
        for(int i = 0; i < REGS + 1; i++)
        {
            for(int j = 0; j < REGS + 1; j++)
            {
                matrix[i][j] = (i == j ? 1 : 0);
            }
        }
        return matrix;
    }

    // public static int[][] VecMul(int[] vector, int[][] matr2)
    // {
    //     int []result = new int[vector.length()];
    //     for(int i = 0; i < result.length(); i++)
    //     {
    //         result[i] = 0;
    //         for(int j = 0; j < result.length(); j++)
    //         {
    //             result[i] += vector[j] * matr2[j][i];
    //         }
    //     }
    //     return result;
    // }

    public static int[][] MatMul(int[][] matr1, int[][] matr2)
    {
        int [][]result = new int[REGS + 1][REGS + 1];
        for(int i = 0; i < REGS + 1; i++)
        {
            for(int j = 0; j < i; j++)
            {
                int temp = matr2[i][j];
                matr2[i][j] = matr2[j][i];
                matr2[j][i] = temp;
            }
        }
        for(int i = 0; i < REGS + 1; i++)
        {
            for(int j = 0; j < i; j++)
            {
                result[i][j] = 0;
                for(int k = 0; k < REGS + 1; k++)
                {
                    result[i][j] += matr1[i][k] * matr2[j][k];
                }
            }
        }
        return result;
    }

    public static int[][] Pow(int[][] matr, int pow)
    {
        if(pow == 0)
        {
            return Identity();
        }
        if(pow % 2 == 0)
        {
            return Pow(MatMul(matr, matr), pow / 2);
        }
        else
        {
            return MatMul(matr, Pow(matr, pow - 1));
        }
    }

    public static int[][] MovReg(int first_index, int second_index)
    {
        int [][]temp = Identity();
        temp[first_index][first_index] = 0;
        temp[second_index][first_index] = 1;
        return temp;
    }

    public static int[][] MovVal(int _index, int value)
    {
        int [][]temp = Identity();
        temp[_index][_index] = 0;
        temp[REGS][_index] = value;
        return temp;
    }

    public static int[][] AddReg(int first_index, int second_index)
    {
        int [][]temp = Identity();
        temp[second_index][first_index] = 1;
        return temp;
    }

    public static int[][] AddVal(int _index, int value)
    {
        int [][]temp = Identity();
        temp[REGS][_index] = value;
        return temp;
    }

    public static int[][] SubReg(int first_index, int second_index)
    {
        int [][]temp = Identity();
        temp[second_index][first_index] = -1;
        return temp;
    }

    public static int[][] SubVal(int _index, int value)
    {
        return AddVal(_index, -value);
    }

    public static int[][] MulVal(int _index, int value)
    {
        int [][]temp = Identity();
        temp[_index][_index] = value;
        return temp;
    }

    public static int[][] DoIt(String[] temp)
    {
        int [][]matrix = Identity();
        int [][]current = new int[REGS + 1][REGS + 1];
        for(int i = 0; i < REGS; i++)
        {
            regs_names[i] = String.valueOf(i + Integer.valueOf('a'));
        }
        int i = 0;
        while(true)
        {
            String[] tokens = new String[10];
            tokens = temp[i++].split(" ");
            if(tokens[0] == "loop")
            {
                current = Pow(DoIt(temp), Integer.valueOf(tokens[1]));
            }
            else if(tokens[0] == "end")
            {
                return matrix;
            }
            else
            {
                int first_index = regs_names;
            }
        }
    }
 
    public static void main(String[] args) throws Exception
    {
        String fileInputPath = "input.txt";
        File xmlFileInput = new File(fileInputPath);
        String fileOutputPath = "output.txt";
        File xmlFileOutput = new File(fileOutputPath);
        // DoIt(Reader(xmlFileInput));
        // System.out.println(REGS);
        // Identity();
        // Writer(Reader(xmlFileInput).toString(), xmlFileOutput);
    }
}
