package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Reading grammars from files
        String[][] grammar_lab2 = readFromFile("in1.txt");
        String[][] grammar_lab3 = readFromFile("in2.txt");

        // Words to check
        String word1 = "abababbbaabbbabababbbbbb";
        String word2 = "(()((([][][[[]]])))(()()))";

        // Creating checkers
        GrammarChecker gc1 = new GrammarChecker(grammar_lab2);
        GrammarChecker gc2 = new GrammarChecker(grammar_lab3);

        // Get result
        boolean w1 = gc1.checkWord(word1);
        boolean w2 = gc2.checkWord(word2);

        // Output result
        printResult(w1, word1);
        printResult(w2, word2);
    }

    private static void printResult(boolean res, String word){
        if (res){
            System.out.printf("Word \"%s\" is accepted\n", word);
        }else {
            System.out.printf("Word \"%s\" is rejected\n", word);
        }
    }

    private static String[][] readFromFile(String s) throws FileNotFoundException {

        Scanner in = new Scanner(new File(s));

        ArrayList<String[]> list = new ArrayList<>();
        while (in.hasNext()){
            list.add(in.nextLine().split(" "));
        }

        return list.toArray(new String[list.size()][]);
    }

}
