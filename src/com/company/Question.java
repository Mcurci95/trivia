package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

// Make a subclass of questions be true of false?

public class Question {
    private String difficulty;
    private String question;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;
    private String category;
    private String type;
    private ArrayList<String> shuffledList;

    public Question(String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswers, String category, String type) {
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.category = category;
        this.type = type;
    }

    void printQuestion() {
        System.out.println(this.question);
        for (int i = 0; i < this.shuffledList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + shuffledList.get(i));
        }
    }

    private ArrayList<String> shuffle() {
        ArrayList<String> shuffledList = new ArrayList<>();
        shuffledList.add(this.correctAnswer);
        shuffledList.addAll(this.incorrectAnswers);
        Collections.shuffle(shuffledList);
        return shuffledList;
    }

    Question(JSONObject questionObj) {
        this.difficulty = questionObj.getString("difficulty");
        this.question = questionObj.getString("question");
        this.correctAnswer = questionObj.getString("correct_answer");
        this.incorrectAnswers = incorrectAnswerList(questionObj.getJSONArray("incorrect_answers"));
        this.category = questionObj.getString("category");
        this.type = questionObj.getString("type");
        this.shuffledList = shuffle();
    }

    private ArrayList<String> incorrectAnswerList(JSONArray incorrectAnsJSON) {
        ArrayList<String> incorrectAnswerList = new ArrayList<>();
        for (int i=0; i < incorrectAnsJSON.length(); i++) {
            incorrectAnswerList.add(incorrectAnsJSON.get(i).toString());
        }
        return incorrectAnswerList;
    }

    boolean test(Scanner reader) {

        int guess = reader.nextInt();
        if (this.shuffledList.get(guess - 1) == this.correctAnswer) {
            return true;
        }
        return false;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }
}
