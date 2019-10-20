package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dictionary.View.View_Home;
import com.example.dictionary.View.View_Search;

public class MainActivity extends AppCompatActivity {
    public static int STEP=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        System.out.println("on create main");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop main");
    }
}
