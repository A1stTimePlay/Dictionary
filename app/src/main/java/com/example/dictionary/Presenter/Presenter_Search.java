package com.example.dictionary.Presenter;

import com.example.dictionary.MainActivity;
import com.example.dictionary.View.View_Search;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.dictionary.MainActivity.MAX_MEANING_CHARACTER;

public class Presenter_Search implements IPresenter_Search{
    View_Search view_search;

    public Presenter_Search(View_Search view_search) {
        this.view_search = view_search;
    }

    @Override
    public void findMeaning(int index) {
        if (index==-1){
            view_search.showOnTextView("word not found");
            return;
        } else {
            int currentCharacter;
            StringBuilder meaning = new StringBuilder();
            try {
                FileInputStream fisMeaning = new FileInputStream(MainActivity.MEANING);
                fisMeaning.skip(index * 50);

                currentCharacter = 0;
                while (currentCharacter < MAX_MEANING_CHARACTER) {
                    meaning.append((char) fisMeaning.read());
                    currentCharacter++;
                }
                String resultMeaning = meaning.toString().trim();
                view_search.showOnTextView(resultMeaning);
                fisMeaning.close();

            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }
        }
    }
}
