package com.example.dictionary.Presenter;

import com.example.dictionary.MainActivity;
import com.example.dictionary.View.View_Home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.dictionary.MainActivity.MAX_MEANING_CHARACTER;
import static com.example.dictionary.MainActivity.MAX_WORD_CHARACTER;
import static com.example.dictionary.MainActivity.MEANING_FILE_LENGTH;
import static com.example.dictionary.MainActivity.WORD_FILE_LENGTH;

public class Presenter_Home implements IPresenter_Home {
    View_Home view_home;

    public Presenter_Home(View_Home view_home) {
        this.view_home = view_home;
    }

    @Override
    public void read(int step) {
        if (step*MAX_WORD_CHARACTER> WORD_FILE_LENGTH){
            System.out.println("Step lố size của file");
            return;
        } else {
            int currentCharacter;
            StringBuilder word = new StringBuilder();
            StringBuilder meaning = new StringBuilder();
            try {
                FileInputStream fisWord = new FileInputStream(MainActivity.WORD);
                FileInputStream fisMeaning = new FileInputStream(MainActivity.MEANING);
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
    public void write(String word, String meaning) {

        int currentCharacter;
        char blank = ' ';
        try {
            FileWriter fwWord = new FileWriter(MainActivity.WORD, true);

            FileWriter fwMeaning = new FileWriter(MainActivity.MEANING, true);

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
            WORD_FILE_LENGTH = MainActivity.WORD.length();  // cập nhập lại size của file sau khi thêm vào
            MEANING_FILE_LENGTH = MainActivity.MEANING.length();
        } catch (FileNotFoundException e) {
            System.out.println("Presenter_Home: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Presenter_Home: " + e.getMessage());
        }
    }
}
