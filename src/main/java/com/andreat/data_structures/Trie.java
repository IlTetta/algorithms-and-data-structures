package com.andreat.data_structures;

public class Trie {

    class TrieNode {
        private static final int ALPHABET_SIZE = 26;
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
            isEndOfWord = false;
            for(int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix){
        TrieNode current = root;
        for(int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if(current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }

    public void delete(String word) {
        deleteRec(root, word, 0);
    }

    private boolean deleteRec(TrieNode current, String word, int depth){
        if(depth == word.length()) {
            if(!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;

            for(int i = 0; i < 26; i++) {
                if(current.children[i] != null) {
                    return false;
                }
            }
            return true;
        }

        int index = word.charAt(depth) - 'a';

        if(current.children[index] == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = deleteRec(current.children[index], word, depth + 1);

        if(shouldDeleteCurrentNode){
            current.children[index] = null;

            for(int i = 0; i < 26; i++) {
                if(current.children[i] != null) {
                    return false;
                }
            }
            return !current.isEndOfWord;
        }
        return false;
    }
}
