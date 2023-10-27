package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TextHandler {
    public int getResult() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Enter text:");
                StringBuffer buffer = new StringBuffer(scanner.nextLine());
                System.out.println(buffer+"\n");
                if (buffer.length() == 0 || !buffer.toString().contains("."))
                    throw new Exception();

                String[] sentences = buffer.toString().split("[.!?]+\\s*");
                scanner.close();
                return Calculate(sentences);
            } catch (Exception e) {
                System.err.println("Error: Input Mismatch.\n");
                scanner.nextLine();
            }
        }
    }

    private int Calculate(String[] sentences) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String sentence : sentences) {
            String[] sentenceWords = sentence.split("\\s+");
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

    private boolean isFound(ArrayList<String> allWords, String word) {
        for (String element : allWords)
            if (element.equals(word))
                return true;
        return false;
    }
}
