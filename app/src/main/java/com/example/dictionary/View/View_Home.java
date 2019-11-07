package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Presenter.Presenter_Home;
import com.example.dictionary.R;

public class View_Home extends AppCompatActivity implements IView_Home {
    EditText etWordRead;
    EditText etMeaningRead;
    EditText etWordDelete;
    EditText etWordUpdate;
    EditText etMeaningUpdate;
    EditText etWordAdd;
    EditText etMeaningAdd;
    Presenter_Home presenter_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter_home = new Presenter_Home(this);

        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);
        Button delete = findViewById(R.id.delete);
        Button update = findViewById(R.id.update);
        final EditText etNumber = findViewById(R.id.number);
        etWordAdd = findViewById(R.id.etWordAdd);
        etMeaningAdd = findViewById(R.id.etMeaningAdd);
        etWordRead = findViewById(R.id.etWordRead);
        etMeaningRead = findViewById(R.id.etMeaningRead);
        etWordDelete = findViewById(R.id.etWordDelete);
        etWordUpdate = findViewById(R.id.etWordUpdate);
        etMeaningUpdate = findViewById(R.id.etMeaningUpdate);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWordAdd.getText().toString().trim().toLowerCase();
                String meaning = etMeaningAdd.getText().toString().trim().toLowerCase();
                if (word.length() != 0 && meaning.length() != 0) {
                    presenter_home.write(MainActivity.WORD, MainActivity.MEANING, word, meaning);
                } else {
                    System.out.println("Input all required field");
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNumber.getText().toString().trim().toLowerCase();
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
                String word = etWordDelete.getText().toString().trim().toLowerCase();
                presenter_home.delete(MainActivity.WORD, MainActivity.MEANING, word);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWordUpdate.getText().toString().trim().toLowerCase();
                String meaning = etMeaningUpdate.getText().toString().trim().toLowerCase();
                presenter_home.update(MainActivity.WORD, MainActivity.MEANING, word, meaning);
            }
        });
    }

    @Override
    public void addCallBack(String word, int status) {
        if (status == 0)
            Toast.makeText(this, "Add successful: " + word, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Add unsuccessful: " + word + " already added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void readCallBack(String word, String meaning, int status) {
        if (status == 0) {
            etWordRead.setText(word);
            etMeaningRead.setText(meaning);
            Toast.makeText(this, "Read successful: " + word, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Read unsuccessful: " + word + " not found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCallBack(String word, int status) {
        if (status == 0)
            Toast.makeText(this, "Delete successful: " + word, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Delete unsuccessful: " + word + " not found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCallBack(String word, int status) {
        if (status == 0)
            Toast.makeText(this, "Update successful: " + word, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Update unsuccessful: " + word + " not found", Toast.LENGTH_SHORT).show();
    }

}
