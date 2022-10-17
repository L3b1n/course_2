import java.util.Scanner;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

class Sort
{
    public int compare(Object a, Object b)
    {
        return (int)((double)a - (double)b);
    }
}

public class Bin_task_3 
{
    public static void definition(Double[][] mat, int n) 
    {
        for(int i = 0; i < n; i++) 
        {
            for(int j = 0; j < n; j++){ mat[i][j] = (double)(Math.random() * 51) - 25;}
        }
    }

    public static void print(Double[][] mat, int n) 
    {
        for(int i = 0; i < n - 2; i++) 
        {
            for(int j = 0; j < n; j++){ System.out.print(mat[i][j] + " ");}
            System.out.println();
        }
        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        for(int j = 0; j < n; j++){ System.out.print(numberFormat.format(mat[n - 2][j]) + " ");}
        System.out.println();
        final DecimalFormat decimalFormat = new DecimalFormat("0%");
        for(int j = 0; j < n; j++){ System.out.print(decimalFormat.format(mat[n - 1][j]) + " ");}
        System.out.println();
    }

    public static void multiply(Double[][] mat_final, Double[][] mat1, Double[][] mat2, int size) 
    {
        double m;
        for(int i = 0; i < size; i++) 
        {
            m = 1;
            for(int j = 0; j < size; j++){ m *= mat2[i][j];}
            System.out.println("Произведение " + i + " = " + m);
            for(int j = 0; j < size; j++){ mat_final[j][i] = mat1[j][i] + m;}
        }
    }

    public static void main(String[] args) 
    {
        System.out.print("Введите размерность матрицы: ");
        Scanner scanner = new Scanner(System.in);
        int size;
        size = scanner.nextInt();
        Double [][]matrix1 = new Double[size][size];
        Double [][]matrix2 = new Double[size][size];
        Double [][]matrix_final= new Double[size][size];
        definition(matrix1, size);
        definition(matrix2, size);
        System.out.println("Первая матрица:");
        print(matrix1, size); System.out.println();
        System.out.println("Вторая матрица:");
        print(matrix2, size); System.out.println();
        multiply(matrix_final, matrix1, matrix2, size); System.out.println();
        System.out.println("Итоговая матрица:");
        print(matrix_final, size); System.out.println();
        System.out.print("Введите с какого элемента сортировать строку и на каком закончить: ");
        int i = scanner.nextInt(), j = scanner.nextInt();
        Arrays.sort(matrix_final[0], i, j);
        System.out.println("Матрица с отсортированной первой строкой от " + i + " до " + j + " элемента:");
        print(matrix_final, size); System.out.println();
        Double[] copy = Arrays.copyOf(matrix_final[0], j - i);
        System.out.println("Массив скопированный от " + i + " до " + j + " элемента:");
        System.out.println(Arrays.toString(copy));
        scanner.close();
    }
}