package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class View_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final EditText et = findViewById(R.id.Word);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);

        final File word = new File(View_Home.this.getFilesDir(), "word.txt");
        if (!word.exists()) {
            try {
                word.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final File meaning = new File(View_Home.this.getFilesDir(), "meaning.txt");
        if (!meaning.exists()) {
            try {
                meaning.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et.getText().toString().trim();
                if (data.length() != 0) {
                    try {
                        FileWriter writer = new FileWriter(word, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        bufferedWriter.write(data);
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                try {
                    FileReader reader = new FileReader(word);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result.append(line).append("\n");
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            }
        });
    }
}
