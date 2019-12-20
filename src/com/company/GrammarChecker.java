package com.company;

public class GrammarChecker {

    String[][] grammar;

    public GrammarChecker(String[][] grammar) {
        this.grammar = grammar;
    }

    boolean checkWord(String word){

        String st;
        StringBuilder r;

        String[][] ans_mat = new String[word.length()][word.length()];

        // Fill the diagonal of the matrix (first iteration of algorithm)
        for(int i = 0; i < word.length(); i++){
            r = new StringBuilder();
            st = "" + word.charAt(i);
            for (String[] strings : grammar) {
                for (int j = 1; j < strings.length; j++) {
                    if (strings[j].equals(st)) {
                        r.append(strings[0]);
                    }
                }
            }
            ans_mat[i][i] = r.toString();
        }

        //Fill the rest of the matrix
        for (int j = 1; j < ans_mat.length; j++) {
            for (int i = 0; i < ans_mat.length - j; i++) {
                String value = getValueForPosition(i, i + j, ans_mat);
                ans_mat[i][i + j] = value;
            }
        }

        //The last column of first row should have the start symbol
        return ans_mat[0][word.length() - 1].contains("S");
    }

    String getValueForPosition(int posI, int posJ, String[][] ans_mat){

        // Количество элементов на спуск
        int n = posJ-posI;

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            temp.append(combine(ans_mat[posI][posI + i], ans_mat[posI + i + 1][posJ]));
        }

        return temp.toString();

    }

    //Checks if the passed string can be achieved for the grammar
    private String check(String a){
        StringBuilder result = new StringBuilder();
        for (String[] strings : grammar) {
            for (int i = 1; i < strings.length; i++) {
                if (strings[i].equals(a)) {
                    result.append(strings[0]);
                }
            }
        }
        return result.toString();
    }

    //Makes all possible combinations out of the two string passed
    private String combine(String a, String b){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j < b.length(); j++){
                result.append(check(a.charAt(i) + "" +  b.charAt(j)));
            }
        }
        return result.toString();
    }
}
