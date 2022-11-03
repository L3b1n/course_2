import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bin_task_5 
{
    public static String isTrue(String text) 
    {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){ return "True";} 
        else{ return "False";}
    }

    public static void main(String[] args) throws Exception 
    {
        FileReader input = new FileReader("input.txt");
        FileWriter output = new FileWriter("output.txt");
        Scanner scan = new Scanner(input);
        while(scan.hasNextLine()){ output.write(isTrue(scan.nextLine()) + "\n");}
        input.close();
        output.close();
    }
}