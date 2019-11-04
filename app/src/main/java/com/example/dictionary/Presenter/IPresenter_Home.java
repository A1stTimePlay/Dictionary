package com.example.dictionary.Presenter;

import java.io.File;

public interface IPresenter_Home {
    void read(File fileWord, File fileMeaning, int step);
    void write(File fileWord, File fileMeaning, String word, String meaning);
    void delete(File fileWord, File fileMeaning, String word);
}
