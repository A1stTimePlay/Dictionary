package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Model.Trie;
import com.example.dictionary.Presenter.Presenter_Home;
import com.example.dictionary.R;

public class View_Home extends AppCompatActivity implements IView_Home {
    EditText etWordRead;
    EditText etMeaningRead;
    EditText etWordDelete;
    Presenter_Home presenter_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter_home = new Presenter_Home(this);
        final EditText etWord = findViewById(R.id.etWordAdd);
        final EditText etMeaning = findViewById(R.id.etMeaningAdd);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);
        Button delete = findViewById(R.id.delete);
        final EditText etNumber = findViewById(R.id.number);
        etWordRead = findViewById(R.id.etWordRead);
        etMeaningRead = findViewById(R.id.etMeaningRead);
        etWordDelete = findViewById(R.id.etWordDelete);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWord.getText().toString().trim().toLowerCase();
                String meaning = etMeaning.getText().toString().trim().toLowerCase();
                if (word.length() != 0 && meaning.length() != 0) {
                    presenter_home.write(MainActivity.WORD, MainActivity.MEANING ,word, meaning);
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
                        presenter_home.read(MainActivity.WORD, MainActivity.MEANING, step);
                } else {
                    System.out.println("Input all required field");
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void showOnTextView(String word, String meaning) {
        etWordRead.setText(word);
        etMeaningRead.setText(meaning);
    }

    @Override
    public void deleteCallBack() {

    }

}
