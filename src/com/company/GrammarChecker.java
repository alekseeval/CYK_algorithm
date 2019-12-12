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
        for(int i = 1; i < word.length(); i++){
            for(int j = i; j < word.length(); j++){
                r = new StringBuilder();
                for(int k = j - i; k < j; k++){
                    r.append(combine(ans_mat[j - i][k], ans_mat[k + 1][j]));
                }
                ans_mat[j - i][j] = r.toString();
            }
        }

        //The last column of first row should have the start symbol
        return ans_mat[0][word.length() - 1].contains("S");
    }

    //Checks if the passed string can be achieved for the grammar
    private String check(String a){
        StringBuilder to_ret = new StringBuilder();
        int count;
        for (String[] strings : grammar) {
            for (count = 0; count < strings.length; count++) {
                if (strings[count].equals(a)) {
                    to_ret.append(strings[0]);
                }
            }
        }
        return to_ret.toString();
    }

    //Makes all possible combinations out of the two string passed
    private String combine(String a, String b){
        StringBuilder to_ret = new StringBuilder();
        String temp;
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j < b.length(); j++){
                temp = a.charAt(i) + "" +  b.charAt(j);
                to_ret.append(check(temp));
            }
        }
        return to_ret.toString();
    }
}
