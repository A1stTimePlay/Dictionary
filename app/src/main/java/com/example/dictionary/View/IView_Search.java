package com.example.dictionary.View;

import com.example.dictionary.Model.Trie;

public interface IView_Search {
    void createTrieCallBack(Trie trie);
    void showOnTextView(String result);
}
