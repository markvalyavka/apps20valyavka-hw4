package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.*;

/**
 * @author andrii
 */
public class PrefixMatches {

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int wordCount = 0;
        for (String string : strings) {
            String[] wordsArr = string.split(" ");
            for (String word : wordsArr) {
                if (word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                    wordCount++;
                }
            }
        }
        return wordCount;
    }

    public void checkPrefix(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        checkPrefix(pref);
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        checkPrefix(pref);

        // Store all words with specific prefix in a list
        List<String> result = new ArrayList<String>();
        for (String str : trie.wordsWithPrefix(pref)) {
            result.add(str);
        }

        // Sort list by string length
        result.sort(Comparator.comparingInt(String::length));
        ArrayList<String> resultWithKLength = new ArrayList<>();

        // We are allowed to store only k different string lengths,
        // So we keep track when string length changes and if number
        // of changes exceeds k, we can break and return the result
        int differentlengthCount = 0;
        int currLength = 0;

        for (String s : result) {
            if (s.length() != currLength) {
                currLength = s.length();
                differentlengthCount++;
            }
            if (differentlengthCount > k) {
                break;
            }
            resultWithKLength.add(s);
        }
        return resultWithKLength;
    }

    public int size() {
        return trie.size();
    }
}
