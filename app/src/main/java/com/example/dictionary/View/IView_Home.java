package com.example.dictionary.View;

public interface IView_Home {
    void addCallBack(String word, int status);
    void readCallBack(String word, String meaning, int status);
    void deleteCallBack(String word, int status);
    void updateCallBack(String word, int status);
}
