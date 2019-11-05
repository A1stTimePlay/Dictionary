package com.example.dictionary.Presenter;

import com.example.dictionary.Model.Trie;
import com.example.dictionary.View.View_Home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.dictionary.MainActivity.MAX_MEANING_CHARACTER;
import static com.example.dictionary.MainActivity.MAX_WORD_CHARACTER;
import static com.example.dictionary.MainActivity.tempMEANING;
import static com.example.dictionary.MainActivity.tempWORD;

public class Presenter_Home implements IPresenter_Home {
    View_Home view_home;

    public Presenter_Home(View_Home view_home) {
        this.view_home = view_home;
    }

    @Override
    public void read(File fileWord, File fileMeaning, int index) {
        if (index * MAX_WORD_CHARACTER > fileWord.length()) {
            System.out.println("Step lố size của file");
            return;
        } else {
            int currentCharacter;
            StringBuilder word = new StringBuilder();
            StringBuilder meaning = new StringBuilder();
            try {
                FileInputStream fisWord = new FileInputStream(fileWord);
                FileInputStream fisMeaning = new FileInputStream(fileMeaning);
                fisWord.skip(index * MAX_WORD_CHARACTER);
                fisMeaning.skip(index * MAX_MEANING_CHARACTER);

                currentCharacter = 0;
                while (currentCharacter < MAX_WORD_CHARACTER) {
                    word.append((char) fisWord.read());
                    currentCharacter++;
                }
                String resultWord = word.toString().trim();

                currentCharacter = 0;
                while (currentCharacter < MAX_MEANING_CHARACTER) {
                    meaning.append((char) fisMeaning.read());
                    currentCharacter++;
                }
                String resultMeaning = meaning.toString().trim();

                view_home.showOnTextView(resultWord, resultMeaning);
                fisWord.close();
                fisMeaning.close();

            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }
        }
    }

    @Override
    public void write(File fileWord, File fileMeaning, String word, String meaning) {
        // Tạo trie để tìm xe chữ đã có trong file hay chưa
        Trie trie = new Trie();
        trie.create(fileWord, MAX_WORD_CHARACTER);

        int currentCharacter;
        char blank = ' ';
        if (trie.search(word) != -1) {
            System.out.println("Word already added");
        } else {
            try {
                FileWriter fwWord = new FileWriter(fileWord, true);

                FileWriter fwMeaning = new FileWriter(fileMeaning, true);

                currentCharacter = 0;
                while (currentCharacter < MAX_WORD_CHARACTER) {
                    for (; currentCharacter < word.length(); currentCharacter++) {
                        fwWord.append(word.charAt(currentCharacter));
                    }
                    currentCharacter++;
                    fwWord.append(blank);
                }


                currentCharacter = 0;
                while (currentCharacter < MAX_MEANING_CHARACTER) {
                    for (; currentCharacter < meaning.length(); currentCharacter++) {
                        fwMeaning.append(meaning.charAt(currentCharacter));
                    }
                    currentCharacter++;
                    fwMeaning.append(blank);
                }

                fwWord.close();
                fwMeaning.close();
            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(File fileWord, File fileMeaning, String word) {
        // Tạo trie để tìm xem chữ đã có trong file hay chưa
        Trie trie = new Trie();
        trie.create(fileWord, MAX_WORD_CHARACTER);

        int index = trie.search(word);
        int byteRead;
        if (index == -1) {
            System.out.println("word not found");
            return;
        } else {
            try {
                // Xóa chữ trong file Word

                // Copy các chữ không xóa vào file tạm tempWORD
                FileInputStream fisWord = new FileInputStream(fileWord);
                FileOutputStream fosTempWord = new FileOutputStream(tempWORD);
                for (int i = 0; i < fileWord.length() / MAX_WORD_CHARACTER; i++) {
                    if (i == index) fisWord.skip(MAX_WORD_CHARACTER);
                    else {
                        for (int j = 0; j < MAX_WORD_CHARACTER; j++) {
                            fosTempWord.write(fisWord.read());
                        }
                    }
                }
                fisWord.close();
                fosTempWord.close();

                // Copy các chữ trong file tạm tempWord vào lại file gốc WORD
                FileInputStream fisTempWord = new FileInputStream(tempWORD);
                FileOutputStream fosWord = new FileOutputStream(fileWord);

                while ((byteRead = fisTempWord.read()) != -1) {
                    fosWord.write(byteRead);
                }
                fisTempWord.close();
                fosWord.close();

                // Xóa chữ trong file MEANING
                // Copy các chữ không xóa vào file tạm tempMEANING
                FileInputStream fisMeaning = new FileInputStream(fileMeaning);
                FileOutputStream fosTempMeaning = new FileOutputStream(tempMEANING);
                for (int i = 0; i < fileMeaning.length() / MAX_MEANING_CHARACTER; i++) {
                    if (i == index) fisMeaning.skip(MAX_MEANING_CHARACTER);
                    else {
                        for (int j = 0; j < MAX_MEANING_CHARACTER; j++) {
                            fosTempMeaning.write(fisMeaning.read());
                        }
                    }
                }
                fisMeaning.close();
                fosTempMeaning.close();

                // Copy các chữ trong file tạm tempMEANING vào lại file gốc MEANING
                FileInputStream fisTempMeaning = new FileInputStream(tempMEANING);
                FileOutputStream fosMeaning = new FileOutputStream(fileMeaning);
                while ((byteRead = fisTempMeaning.read()) != -1) {
                    fosMeaning.write(byteRead);
                }
                fisTempMeaning.close();
                fosMeaning.close();

            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }
        }

    }

    @Override
    public void update(File fileWord, File fileMeaning, String word, String meaning) {
        Trie trie = new Trie();
        trie.create(fileWord, MAX_WORD_CHARACTER);

        int index = trie.search(word);
        int byteRead;

        if (index == -1) {
            System.out.println("Word not found");
            return;
        } else {
            try {
                FileInputStream fisMeaning = new FileInputStream(fileMeaning);
                FileOutputStream fosTempMeaning = new FileOutputStream(tempMEANING);
                for (int i = 0; i < fileMeaning.length() / MAX_MEANING_CHARACTER; i++) {
                    if (i == index) {
                        fisMeaning.skip(MAX_MEANING_CHARACTER);
                        int current_character = 0;
                        while (current_character<MAX_MEANING_CHARACTER){
                            while (current_character < meaning.length()){
                                fosTempMeaning.write(meaning.charAt(current_character));
                                current_character++;
                            }
                            fosTempMeaning.write(' ');
                            current_character++;
                        }
                    }
                    else {
                        for (int j = 0; j < MAX_MEANING_CHARACTER; j++) {
                            fosTempMeaning.write(fisMeaning.read());
                        }
                    }

                    FileInputStream fisTempMeaning = new FileInputStream(tempMEANING);
                    FileOutputStream fosMeaning = new FileOutputStream(fileMeaning);
                    while ((byteRead = fisTempMeaning.read()) != -1) {
                        fosMeaning.write(byteRead);
                    }
                    fisTempMeaning.close();
                    fisMeaning.close();
                }
                fisMeaning.close();
                fosTempMeaning.close();
            } catch (FileNotFoundException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Presenter_Home: " + e.getMessage());
            }

        }
    }
}
