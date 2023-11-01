package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Enter text:");
                StringBuffer text = new StringBuffer(scanner.nextLine());

                if (containsCyrillic(text))
                    throw new Exception();

                if (text.length() == 0)
                    throw new Exception();

                System.out.println("\nResult: " + Calculate(text) + "\n");
                scanner.close();
                break;
            } catch (Exception e) {
                System.out.println("Error: Input Mismatch.\n");
            }
        }
    }

    private static int Calculate(StringBuffer text) {
        Table wordCount = new Table();
        ArrayList<StringBuffer> sentences = getSentences(text);
        for (StringBuffer sentence : sentences) {
            ArrayList<StringBuffer> words = getWords(sentence);
            ArrayList <StringBuffer> allWords = new ArrayList<StringBuffer>();
            for (StringBuffer word : words)
                if (!isFound(allWords, word)) {
                    allWords.add(word);
                    wordCount.addRecord(word);
                }
        }
        return wordCount.getMax();
    }

    private static ArrayList<StringBuffer> getSentences(StringBuffer text) {
        ArrayList<StringBuffer> sentences = new ArrayList<>();
        ArrayList<Integer> separators = new ArrayList<>();

        separators.add(0);
        for (int i = 0; i < text.length() - 1; i++) {
            char ch = text.charAt(i);
            if ((ch == '.' || ch == '!' || ch == '?') && text.charAt(i + 1) == ' ') {
                separators.add(i + 2);
            }
        }
        separators.add(text.length());

        for (int i = 0; i < separators.size() - 1; i++) {
            int pos1 = separators.get(i);
            int pos2 = Math.min(separators.get(i + 1), text.length());
            StringBuffer sentence = new StringBuffer(text.substring(pos1, pos2));
            sentences.add(sentence);
        }

        return sentences;
    }

    private static ArrayList<StringBuffer> getWords(StringBuffer sentence) {
        ArrayList<StringBuffer> words = new ArrayList<>();
        ArrayList<Integer> separators = new ArrayList<>();

        separators.add(0);
        for (int i = 0; i < sentence.length() - 1; i++) {
            char ch = sentence.charAt(i);
            if (ch == ' ' || ch == '-' || ch == '@')
                separators.add(i + 1);
        }
        separators.add(sentence.length());

        for (int i = 0; i < separators.size() - 1; i++) {
            int pos1 = separators.get(i);
            int pos2 = Math.min(separators.get(i + 1), sentence.length());
            StringBuffer word = new StringBuffer(sentence.substring(pos1, pos2));

            for (int j = word.length() - 1; j >= 0; j--) {
                char ch = word.charAt(j);
                if (isForbidden(ch)) 
                    word.deleteCharAt(j);
            }

            toLowerCase(word);
            words.add(word);
        }

        return words;
    }

    private static boolean isForbidden(char ch) {
        char[] forbiddens = {'.', '!', '?', ' ', ',',
            ';', ':', '(', ')', '"', '\'', '@', '`'};

        for (char forbidden : forbiddens)
            if (ch == forbidden)
                return true;

        return false;
    }

    public static void toLowerCase(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            char currentChar = stringBuffer.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                char convertedChar = Character.toLowerCase(currentChar);
                stringBuffer.setCharAt(i, convertedChar);
            }
        }
    }

    private static boolean isFound(ArrayList<StringBuffer> allWords, StringBuffer word) {
        for (StringBuffer element : allWords)
            if (equals(element, word))
                return true;
        return false;
    }

    public static boolean equals(StringBuffer a, StringBuffer b) {
        if (a.length() != b.length())
            return false;
        
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                return false;
        
        return true;
    }

    private static boolean containsCyrillic(StringBuffer input) {
        Pattern pattern = Pattern.compile("[а-яА-Я]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}