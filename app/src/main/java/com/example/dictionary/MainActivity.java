package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dictionary.View.View_Home;
import com.example.dictionary.View.View_Search;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static int INDEX = 0;
    public static File WORD;
    public static File MEANING;
    public static final int MAX_WORD_CHARACTER = 10;
    public static final int MAX_MEANING_CHARACTER = 50;
    public static long WORD_FILE_LENGTH;
    public static long MEANING_FILE_LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WORD = new File(this.getFilesDir(), "word.txt");
        MEANING = new File(this.getFilesDir(), "meaning.txt");
        WORD_FILE_LENGTH = WORD.length();
        MEANING_FILE_LENGTH = MEANING.length();

        try {
            if (!WORD.exists()) {
                WORD.createNewFile();
            }
            if (!MEANING.exists()) {
                MEANING.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_Home.class);
                startActivity(intent);
            }
        });
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_Search.class);
                startActivity(intent);
            }
        });
    }

}
