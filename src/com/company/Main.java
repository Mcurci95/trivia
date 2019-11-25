package com.company;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException {
        List<Question> questions = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        Question question1;
        questions = Connection.generateQuestions("https://opentdb.com/api.php?amount=10");
        question1 = questions.get(0);
        question1.printQuestion();
        Integer input = reader.nextInt();
        if (question1.test(input)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong!, correct answer is " + question1.getCorrectAnswer());
        }
    }
}
