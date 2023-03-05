package dev.xpple.mathsparserjava;

import dev.xpple.mathsparserjava.objects.MathsObject;
import dev.xpple.mathsparserjava.parser.MathsParser;

import java.util.Scanner;

public class MathsParserJava {
    public static void main(String[] args) {
        System.out.println("Enter your mathematical object below.");
        Scanner scanner = new Scanner(System.in);
        String mathsString = scanner.nextLine();
        MathsObject mathsObject = MathsParser.fromString(mathsString);
        System.out.println(mathsObject);
    }
}
