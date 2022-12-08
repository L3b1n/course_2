package com.example;

import java.io.*;
import java.util.*;

public class Main
{
    public static boolean isNum(String str) 
    {
        try {
            Integer.parseInt(str);
        } 
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNum(String str, int rad) 
    {
        try {
            Integer.parseInt(str, rad);
        } 
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isPalindrome(String str) 
    {
        if(isNum(str)){ return false;}

        StringBuilder s1 = new StringBuilder(str);
        StringBuilder s2 = new StringBuilder(str);
        s2.reverse();
        return s1.compareTo(s2) == 0;
    }

    public static void main(String[] args) throws IOException
    {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter string of tokens: ");
            String str1 = scanner.nextLine();
            System.out.print("Enter string of separators: ");
            String str2 = scanner.nextLine();
            System.out.print("Enter number to find: ");
            int find_num = scanner.nextInt();
            String[] words = new String[str1.length()];

            if(str2.length() == 1){ words = str1.split(str2);}
            else 
            {
                StringTokenizer string_tokenizer = new StringTokenizer(str1, str2);
                int i = 0;
                while(string_tokenizer.hasMoreTokens()) 
                {
                    words[i++] = string_tokenizer.nextToken();
                }
            }
            List<String> list = new ArrayList<>(Arrays.asList(words));
            list.removeAll(Arrays.asList("", null));
            words = list.toArray(new String[0]);
            
            System.out.println("_________________________________");

            System.out.println("Print all words: ");
            for(String word : words){ System.out.println(word);}
            System.out.println("_________________________________");

            ArrayList<Integer> oct_numbers = new ArrayList<>();
            ArrayList<Integer> all_numbers = new ArrayList<>();
            ArrayList<String> sep_words = new ArrayList<>();
            ArrayList<String> all_words = new ArrayList<>();
            for(String s : words) 
            {
                if(isNum(s)) 
                {
                    if(isNum(s, 8)){ oct_numbers.add(Integer.parseInt(s));}
                    all_numbers.add(Integer.parseInt(s));
                }
                else if(!isNum(s)){ sep_words.add(s);}
                all_words.add(s);
            }

            System.out.println("Print oct_numbers: ");
            for(Integer num : oct_numbers){ System.out.println(num);}
            System.out.println("_________________________________");

            System.out.println("Print all_numbers: ");
            for(Integer num : all_numbers){ System.out.println(num);}
            System.out.println("_________________________________");

            System.out.println("Print separate words: ");
            for(String str : sep_words){ System.out.println(str);}
            System.out.println("_________________________________");

            System.out.println("SEP_WORDS WITH PAIR OF IDENTICAL CHARACTERS");
            System.out.println("AND EVEN NUMBER OF CHARACTERS: ");
            ArrayList<String> find_words = new ArrayList<>();
            for(String tstr : sep_words) 
            {
                if(!isPalindrome(tstr)){ find_words.add(tstr);}
            }
            for(String str : find_words){ System.out.println(str);}
            System.out.println("_________________________________");

            System.out.println("Index of find number: ");
            int ind = -1;
            for(Integer num : all_numbers) 
            {
                if(find_num == num){ ind = str1.indexOf(String.valueOf(find_num));}
            }
            System.out.println(ind);

            System.out.println("First string + random number: ");
            StringBuffer strBuffer1 = new StringBuffer(str1);
            int temp = (int)((Math.random() * (9 - 1)) + 1);
            strBuffer1.insert(ind == -1 ? strBuffer1.length() / 2 : ind + 1, temp);
            System.out.println("Temp:" + temp);
            System.out.println(strBuffer1);
            System.out.println("_________________________________");

            String smallest_substring_int = all_words.get(0);
            for(String num : all_words) 
            {
                if(String.valueOf(num).length() < String.valueOf(smallest_substring_int).length() && isNum(num.substring(0, 0))){ smallest_substring_int = num;}
            }
            System.out.println("Smallest substring: ");
            System.out.println(smallest_substring_int);
            System.out.println("Input string after replace: ");
            StringBuffer strBuffer2 = new StringBuffer(str1);
            strBuffer2.delete(strBuffer2.lastIndexOf(smallest_substring_int), smallest_substring_int.length());
            System.out.println(strBuffer2);
            System.out.println("_________________________________");

            System.out.println("Lambda collections sort: ");
            Collections.sort(list, (o1, o2) -> String.valueOf(o1.length()).compareTo(String.valueOf(o2.length())));
            System.out.println(list);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}