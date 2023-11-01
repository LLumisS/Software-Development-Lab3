package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] sentences;

        while(true) {
            try {
                System.out.println("Enter text:");
                StringBuffer buffer = new StringBuffer(scanner.nextLine());

                if (containsCyrillic(buffer))
                    throw new Exception();

                if (buffer.length() == 0 || !buffer.toString().contains("."))
                    throw new Exception();

                sentences = buffer.toString().split("[.!?]+\\s*");

                System.out.println("\nResult: " + Calculate(sentences) + "\n");
                scanner.close();
                break;
            } catch (Exception e) {
                System.err.println("Error: Input Mismatch.\n");
            }
        }
    }

    private static int Calculate(String[] sentences) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String sentence : sentences) {
            String[] sentenceWords = sentence.split("[^\\p{L}]+");
            ArrayList <String> allWords = new ArrayList<String>();
            for (String word : sentenceWords) {
                word = word.toLowerCase();
                if(!isFound(allWords, word)) {
                    allWords.add(word);
                    if(wordCount.containsKey(word))
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    else
                        wordCount.put(word, 1);
                }
            }
        }
        return Collections.max(wordCount.values());
    }

    private static boolean isFound(ArrayList<String> allWords, String word) {
        for (String element : allWords)
            if (element.equals(word))
                return true;
        return false;
    }

    private static boolean containsCyrillic(StringBuffer input) {
        Pattern pattern = Pattern.compile("[а-яА-Я]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}