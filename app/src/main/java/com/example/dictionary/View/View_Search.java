package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Model.Trie;
import com.example.dictionary.Presenter.IPresenter_Search;
import com.example.dictionary.Presenter.Presenter_Search;
import com.example.dictionary.R;

public class View_Search extends AppCompatActivity implements IView_Search{
    IPresenter_Search presenter_search;
    TextView tvSearchMeaning;
    Trie trie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText etSearchBar = findViewById(R.id.search_bar);
        tvSearchMeaning= findViewById(R.id.tvSearchMeaning);
        ImageView btnSearch = findViewById(R.id.btnSearch);

        trie = new Trie();
        trie.create(MainActivity.WORD, MainActivity.MAX_WORD_CHARACTER);
        presenter_search = new Presenter_Search(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = trie.search(etSearchBar.getText().toString());
                presenter_search.findMeaning(result);
            }
        });
    }

    @Override
    public void showOnTextView(String result) {
        tvSearchMeaning.setText(result);
    }
}
