package com.example.dictionary.Model;

import com.example.dictionary.MainActivity;

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

    public Trie() {
        this.root = new Node();
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
        p.value = MainActivity.INDEX;
        MainActivity.INDEX++;
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
}
