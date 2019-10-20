package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.R;

import java.io.File;
import java.io.IOException;

public class View_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final EditText et = findViewById(R.id.Word);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);

        final File word = new File(View_Home.this.getFilesDir(),"word.txt");
        if (!word.exists()){
            try {
                word.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final File meaning = new File(View_Home.this.getFilesDir(),"meaning.txt");
        if (!meaning.exists()){
            try {
                meaning.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        write.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data= et.getText().toString().trim();
//                if (data.length()!=0) {
//                    try {
//
//                        while (data.length() < 50) {
//                            data += " ";
//                        }
//                        FileWriter writer=new FileWriter(file,true);
//                        writer.write(data);
//                        writer.close();
//                    } catch (IOException e) {
//                        Log.e("Exception", "File write failed: " + e.toString());
//                    }
//                }
//            }
//        });
//        read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ret = "";
//
//                try {
//                    InputStream inputStream = getApplicationContext().openFileInput("test.txt");
//
//                    if ( inputStream != null ) {
//                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                        String receiveString = "";
//                        StringBuilder stringBuilder = new StringBuilder();
//
//                        while ( (receiveString = bufferedReader.readLine()) != null ) {
//                            stringBuilder.append(receiveString);
//                        }
//
//                        inputStream.close();
//                        ret = stringBuilder.toString();
//                    }
//                }
//                catch (FileNotFoundException e) {
//                    Log.e("login activity", "File not found: " + e.toString());
//                } catch (IOException e) {
//                    Log.e("login activity", "Can not read file: " + e.toString());
//                }
//                System.out.println(ret);
//            }
//        });
    }
}
