package com.andyrewlee.satreview;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 11/25/15.
 */
public class WordsFactory {
    private static WordsFactory wordsFactory;
    private List<Word> words;

    public static WordsFactory get(Context context) {
        if(wordsFactory == null) {
            wordsFactory = new WordsFactory(context);
        }
        return wordsFactory;
    }

    public WordsFactory(Context context) {
        words = new ArrayList<>();
        words.add(new Word("Bigot", "narrow-minded, prejudiced person"));
        words.add(new Word("Counterfeit", "fake; false"));
        words.add(new Word("Enfranchise", "give voting rights"));
        words.add(new Word("Hamper", "hinder; obstruct"));
        words.add(new Word("Kindle", "to start a fire"));
        words.add(new Word("Noxious", "harmful; poisonous; lethal"));
        words.add(new Word("Placid", "calm; peaceful"));
        words.add(new Word("Remuneration", "payment for work done"));
        words.add(new Word("Talisman", "lucky charm"));
    }

    public List<Word> getWords() {
        return words;
    }

    @Nullable
    public Word getWord(String someWord) {
        for(Word word : words) {
            if(word.getWord().equals(someWord)) {
                return word;
            }
        }
        return null;
    }
}
