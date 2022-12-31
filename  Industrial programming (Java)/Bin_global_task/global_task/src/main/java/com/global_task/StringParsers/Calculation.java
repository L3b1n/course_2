package com.global_task.StringParsers;

import java.io.File;
import java.util.Scanner;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.mariuszgromada.math.mxparser.Expression;

public class Calculation {
    public static String CalculationOfLine(String temp_line) {
        Expression newExpression = new Expression(temp_line);
        if(newExpression.checkSyntax()) {
            double result = newExpression.calculate();
            if(Double.isNaN(result)) {
                return devisionError;
            }
            return String.valueOf(result);
        }
        return temp_line;
    }

    public static void Calculate(String inputName, String outputName) throws IOException {
        File inputFile = new File(inputName);
        FileFilter outputFile = (FileFilter) new FileWriter(outputName);
        Scanner reader = new Scanner(inputFile);
        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            String calculatedLine = CalculationOfLine(line);
            ((OutputStreamWriter) outputFile).write(calculatedLine);
        }
        ((OutputStreamWriter) outputFile).close();
        reader.close();
    }

    private static final String devisionError = "Error: devision by zero";
}
