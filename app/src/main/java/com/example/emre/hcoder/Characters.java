package com.example.emre.hcoder;

/**
 * Created by emre on 11/8/17.
 */
class Characters{
    char character;
    int frequency;
    Characters left, right;

    public void setCharacter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    Characters(char ch, int fre){
        character = ch;
        frequency = fre;
        left = null;
        right = null;
    }
}
