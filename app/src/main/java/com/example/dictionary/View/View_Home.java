package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Presenter.Presenter_Home;
import com.example.dictionary.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class View_Home extends AppCompatActivity implements IView_Home {
    TextView tvWord;
    TextView tvMeaning;

    Presenter_Home presenter_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter_home = new Presenter_Home(this);
        final EditText etWord = findViewById(R.id.Word);
        final EditText etMeaning = findViewById(R.id.Meaning);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);
        final EditText etNumber = findViewById(R.id.number);
        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWord.getText().toString().trim().toLowerCase();
                String meaning = etMeaning.getText().toString().trim().toLowerCase();
                if (word.length() != 0 && meaning.length() != 0) {
                    presenter_home.write(word, meaning);
                } else {
                    System.out.println("Input all required field");
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNumber.getText().toString().trim();
                if (number.length() != 0) {
                    int step = Integer.parseInt(number);
                    if (step < MainActivity.WORD_FILE_LENGTH / MainActivity.MAX_WORD_CHARACTER) {
                        presenter_home.read(step);
                    }
                } else {
                    System.out.println("Input all required field");
                }
            }
        });
    }

    @Override
    public void showOnTextView(String word, String meaning) {
        tvWord.setText(word);
        tvMeaning.setText(meaning);
    }
}
