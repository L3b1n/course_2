package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class MainTests {
    @Test
    public void FirstTest() {
        String str1 = "1423Test123my1141programm222by3443443JUnit";
        String str2 = "1234";
        String[] words = new String[str1.length()];
        if(str2.length() == 1){ words = str1.split(str2);}
        else 
        {
            StringTokenizer string_tokenizer = new StringTokenizer(str1, str2);
            int i = 0;
            while(string_tokenizer.hasMoreTokens()){ words[i] = string_tokenizer.nextToken(); i++;}
        }
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.removeAll(Arrays.asList("", null));
        words = list.toArray(new String[0]);

        String expectation[] = { "Test", "my", "programm", "by", "JUnit"};

        assertArrayEquals(expectation, words);
    }

    @Test
    public void SecondTest() {
        String str1 = "aabba1a1aNexta1b1b1b1b1Test";
        String str2 = "b1a";
        String[] words = new String[str1.length()];
        if(str2.length() == 1){ words = str1.split(str2);}
        else 
        {
            StringTokenizer string_tokenizer = new StringTokenizer(str1, str2);
            int i = 0;
            while(string_tokenizer.hasMoreTokens()){ words[i] = string_tokenizer.nextToken(); i++;}
        }
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.removeAll(Arrays.asList("", null));
        words = list.toArray(new String[0]);

        String expectation[] = { "Next", "Test"};

        assertArrayEquals(expectation, words);
    }

    @Test
    public void test_3() {
        String str1 = "test3";
        String str2 = "test3";
        String[] words = new String[str1.length()];
        if(str2.length() == 1){ words = str1.split(str2);}
        else 
        {
            StringTokenizer string_tokenizer = new StringTokenizer(str1, str2);
            int i = 0;
            while(string_tokenizer.hasMoreTokens()){ words[i] = string_tokenizer.nextToken(); i++;}
        }
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.removeAll(Arrays.asList("", null));
        words = list.toArray(new String[0]);

        String expectation[] = {};

        assertArrayEquals(expectation, words);
    }
}

