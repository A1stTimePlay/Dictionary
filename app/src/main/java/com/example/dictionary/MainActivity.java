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
    public static File WORD;
    public static File MEANING;
    public static File tempWORD;
    public static File tempMEANING;
    public static final int MAX_WORD_CHARACTER = 10;
    public static final int MAX_MEANING_CHARACTER = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WORD = new File(this.getFilesDir(), "word.txt");
        MEANING = new File(this.getFilesDir(), "meaning.txt");
        tempWORD = new File(this.getFilesDir(), "temp_word.txt");
        tempMEANING = new File(this.getFilesDir(),"temp_meaning.txt");

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
