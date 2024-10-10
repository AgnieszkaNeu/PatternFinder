package org.example;

import java.util.ArrayDeque;
import java.util.Queue;

public class PatternSearch {
    /*
        Contains the logic responsible for finding a pattern using the Karp-Rabin algorithm,
        returns a queue of indexes of the found pattern for the given text
     */
    public static Queue<Integer> search(String text, String pattern) {

        Queue<Integer> indexes = new ArrayDeque<>();

        if (text.length() < pattern.length()) {
            return indexes;
        }

        KarpRabin karpRabin = new KarpRabin();
        long patternHash = karpRabin.createHash(pattern);
        long textHash = karpRabin.createHash(text.substring(0, pattern.length()));
        karpRabin.setPower(pattern.length());

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (textHash == patternHash) {
                indexes.add(i);
            }
            textHash = karpRabin.moveWindow(textHash, text.charAt(i), text.charAt(i + pattern.length()));
        }
        return indexes;
    }
}
