import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Bin_task_4 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter first str: ");
        String str1 = in.readLine();
        System.out.print("Enter second str: ");
        String str2 = in.readLine();
        System.out.print("Enter number P: ");
        int P = Integer.parseInt(in.readLine());
        StringTokenizer s = new StringTokenizer(str1, str2);
        int[] arr = new int[100];
        int k = 0;
        StringBuilder StringOfNumbers = new StringBuilder("String of Numbers: ");
        StringBuilder StringOfPalindromes = new StringBuilder("String of Palindromes: ");
        StringBuilder StringOfNumberP = new StringBuilder();
        StringBuilder StringOfModified_str1 = new StringBuilder("Modified first str: " + str1);
        Random rand = new Random();

        ArrayList<String> StringOfLexemes = new ArrayList<>();

        while(s.hasMoreTokens()) 
        {
            String str = s.nextToken();
            StringOfLexemes.add(str);
            if(IsNumber(str)) 
            {
                arr[k] = Integer.parseInt(str);
                if(P == arr[k] && StringOfNumberP.length() == 0) 
                {
                    StringOfNumberP.append("Position of number P: ").append(str1.indexOf(Integer.toString(P)));
                    StringOfModified_str1.insert(str1.indexOf(Integer.toString(P)) + 20, rand.nextInt(1000));
                }
                k++;
            }
            else if(IsPalindrome(str)){ StringOfPalindromes.append(str).append(" ");}
        }

        for(int i = 0; i < k; ++i){ StringOfNumbers.append(arr[i]).append(" ");}

        if(str1.contains("(") && str1.contains(")")) 
        {
            StringOfModified_str1.delete(StringOfModified_str1.indexOf("("), StringOfModified_str1.lastIndexOf(")") + 1);
        }

        StringOfLexemes.sort((ss1, ss2) -> {
            if(IsPalindrome(ss1) && IsPalindrome(ss2)) 
            {
                if(ss1.length() % 2 == 1 && ss2.length() % 2 == 1){ return ss1.length() - ss2.length();}
                else if(ss1.length() % 2 == 1){ return -1;}
                else if(ss2.length() % 2 == 1){ return 1;}
                else return 1;
            }
            else if(IsPalindrome(ss1)){ return -1;}
            else if(IsPalindrome(ss2)){ return 1;}
            else{ return 1;}
        });

        System.out.print("String of Lexemes: ");
        StringOfLexemes.forEach(Bin_task_4::PrintString);
        System.out.println("\n" + String.format(StringOfNumbers.toString()));
        System.out.println(String.format(StringOfPalindromes.toString()));
        if(StringOfNumberP.length() == 0) 
        {
            StringOfNumberP.append("Number P hadn't found");
            StringOfModified_str1.insert(20, rand.nextInt(1000));
        }
        System.out.println(String.format(StringOfNumberP.toString()));
        System.out.println(String.format(StringOfModified_str1.toString()));
    }

    private static boolean IsNumber(String str) 
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private static boolean IsPalindrome(String str) 
    {
        if(IsNumber(str)){ return false;}

        StringBuilder s1 = new StringBuilder(str);
        StringBuilder s2 = new StringBuilder(str);
        s2.reverse();
        return s1.compareTo(s2) == 0;
    }

    private static void PrintString(String ss) 
    {
        System.out.print(ss + " ");
    }
}