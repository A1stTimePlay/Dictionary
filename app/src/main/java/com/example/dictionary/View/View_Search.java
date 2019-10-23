package com.example.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dictionary.MainActivity;
import com.example.dictionary.Model.Trie;
import com.example.dictionary.Presenter.Presenter_Search;
import com.example.dictionary.R;

public class View_Search extends AppCompatActivity implements IView_Search{
    private Trie trie;
    Presenter_Search presenter_search;
    TextView tvSearchMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText etSearchBar = findViewById(R.id.search_bar);
        tvSearchMeaning= findViewById(R.id.tvSearchMeaning);
        Button btnSearch = findViewById(R.id.btnSearch);

        trie = new Trie();
        presenter_search = new Presenter_Search(this);
        presenter_search.createTrie(trie);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = trie.search(etSearchBar.getText().toString());
                presenter_search.findMeaning(result);
            }
        });
    }

    @Override
    public void createTrieCallBack(Trie trie) {
        this.trie = trie;
    }

    @Override
    public void showOnTextView(String result) {
        tvSearchMeaning.setText(result);
    }
}
