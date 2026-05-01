package com.wk;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResultClient {

    public static String getCapital(String country) {

        String data = "{\"data\": [{ \"name\": \"Italy\" , \"capital\":\"Rome\"}]}";
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);
            JSONArray array = (JSONArray) object.get("data");
            int size = array.size();
            System.out.println(size);
            JSONObject jsonObject = (JSONObject) array.get(0);
            String capital = (String) jsonObject.get("capital");
            System.out.println(capital);
            return capital;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return "-1";
    }

    public static List<Integer> minChars(List<String> simulations) {
        int available = 0;
        int purchased = 0;
        List<Integer> results = new ArrayList<>();
        for (String s : simulations) {
            for (char c : s.toUpperCase().toCharArray()) {
                switch (c) {
                    case 'C': // new arrival
                    case 'U': // return
                        if (available>0) {
                            available--;
                        } else {
                            purchased++;
                        }
                        break;

                    case 'R': // go meeting
                    case 'L': // leave
                        available++;
                        break;
                    default:
                        new RuntimeException("Invalid character: " + c);
                }
            }
            results.add(purchased);
            available = 0;
            purchased = 0;
        }
        return results;
    }


    public static void main(String[] args) {
        ResultClient.getCapital("123");
    }
}
