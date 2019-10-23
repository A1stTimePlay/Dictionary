package com.example.dictionary.Presenter;

import com.example.dictionary.Model.Trie;

public interface IPresenter_Search {
    void createTrie(Trie trie);
    void findMeaning(int index);
}
