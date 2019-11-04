package com.example.dictionary.Presenter;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Model.Trie;
import com.example.dictionary.View.View_Home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.dictionary.MainActivity.MAX_MEANING_CHARACTER;
import static com.example.dictionary.MainActivity.MAX_WORD_CHARACTER;

public class Presenter_Home implements IPresenter_Home {
    View_Home view_home;

    public Presenter_Home(View_Home view_home) {
        this.view_home = view_home;
    }

    @Override
    public void read(File fileWord, File fileMeaning, int step) {
        if (step*MAX_WORD_CHARACTER> fileWord.length()){
            System.out.println("Step lố size của file");
            return;
        } else {
            int currentCharacter;
            StringBuilder word = new StringBuilder();
            StringBuilder meaning = new StringBuilder();
            try {
                FileInputStream fisWord = new FileInputStream(fileWord);
                FileInputStream fisMeaning = new FileInputStream(fileMeaning);
                fisWord.skip(step * 10);
                fisMeaning.skip(step * 50);

                currentCharacter = 0;
                while (currentCharacter < MAX_WORD_CHARACTER) {
                    word.append((char) fisWord.read());
                    currentCharacter++;
                }
                String resultWord = word.toString().trim();

                currentCharacter = 0;
                while (currentCharacter < MAX_MEANING_CHARACTER) {
                    meaning.append((char) fisMeaning.read());
                    currentCharacter++;
                }
                String resultMeaning = meaning.toString().trim();

                view_home.showOnTextView(resultWord, resultMeaning);
                fisWord.close();
                fisMeaning.close();

            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }
        }
    }

    @Override
    public void write(File fileWord, File fileMeaning, String word, String meaning) {

        int currentCharacter;
        char blank = ' ';
        try {
            FileWriter fwWord = new FileWriter(fileWord, true);

            FileWriter fwMeaning = new FileWriter(fileMeaning, true);

            currentCharacter = 0;
            while (currentCharacter < MAX_WORD_CHARACTER) {
                for (; currentCharacter < word.length(); currentCharacter++) {
                    fwWord.append(word.charAt(currentCharacter));
                }
                currentCharacter++;
                fwWord.append(" ");
            }


            currentCharacter = 0;
            while (currentCharacter < MAX_MEANING_CHARACTER) {
                for (; currentCharacter < meaning.length(); currentCharacter++) {
                    fwMeaning.append(meaning.charAt(currentCharacter));
                }
                currentCharacter++;
                fwMeaning.append(blank);
            }

            fwWord.close();
            fwMeaning.close();
        } catch (FileNotFoundException e) {
            System.out.println("Presenter_Home: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Presenter_Home: " + e.getMessage());
        }
    }

    @Override
    public void delete(String word) {
        Trie trie = new Trie();
        trie.create(MainActivity.WORD, MAX_WORD_CHARACTER);
    }
}
