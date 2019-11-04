package com.example.dictionary.Model;

import com.example.dictionary.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Node {
    Node[] children;
    int value;

    public Node() {
        this.children = new Node[27];
        value = -1;
    }
}

public class Trie {
    private Node root;

    private int wordCount;

    public Trie() {
        this.root = new Node();
        this.wordCount = 0;
    }

    public void insert(String word) {
        Node p = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (index == -65) index = 26;
            if (p.children[index] == null) {
                Node temp = new Node();
                p.children[index] = temp;
                p = temp;
            } else {
                p = p.children[index];
            }
        }
        p.value = this.wordCount;
        this.wordCount++;
    }

    public int search(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (index == -65) index = 26;
            if (p.children[index] == null) {
                return -1;
            } else {
                p = p.children[index];
            }
        }
        return p.value;
    }

    public void create(File file, int max_word_character){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            for (int i = 0; i < file.length() / max_word_character; i++) {
                StringBuilder word = new StringBuilder();
                for (int j = 0; j < max_word_character; j++) {
                    word.append((char) fileInputStream.read());
                }
                String resultWord = word.toString().trim();
                this.insert(resultWord);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Presenter_Search: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Presenter_Search: " + e.getMessage());
        }
    }
}
