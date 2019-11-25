package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    public static List<Question> generateQuestions(String rawURL) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<Question> questions = new ArrayList<Question>();
        try {
            Question question;
            URL url = new URL(rawURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Requests set up
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            // If connection fails
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else { // if connection is successfull
                reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

            JSONObject results =  new JSONObject(responseContent.toString());

            JSONArray jsonArray = results.getJSONArray("results");
            for (int i=0; i < jsonArray.length(); i++) {
                question = new Question(jsonArray.getJSONObject(i));
                questions.add(question);
            }


        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
