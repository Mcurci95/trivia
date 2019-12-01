package com.company;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Question> questions = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        questions = Connection.generateQuestions("https://opentdb.com/api.php?amount=10");
        for (Question q : questions) {
            q.printQuestion();
            if (q.test(reader)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong!, correct answer is " + q.getCorrectAnswer());
            }
        }
    }
}
