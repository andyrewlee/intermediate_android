package com.andyrewlee.satreview;

/**
 * Created by dev1 on 11/25/15.
 */
public class Word {
    private String word;
    private String definition;
    private String notes;

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
