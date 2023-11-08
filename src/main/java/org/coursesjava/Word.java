package org.coursesjava;

import java.util.List;

public class Word {
    private final char[] letters;
    private boolean isRuLetter;

    public Word(String word) {
        this.letters = word.toCharArray();
    }

    public boolean forbidden(List<Character> forbiddenLetters) {
        for (char letter : letters) {
            for (Character forbiddenLetter : forbiddenLetters) {
                if (letter == forbiddenLetter) {
                    isRuLetter = true;
                    break;
                }
            }
        }
        return isRuLetter;
    }
}
