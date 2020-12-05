package ua.edu.ucu;

import ua.edu.ucu.autocomplete.PrefixMatches;
import ua.edu.ucu.collections.ImmutableLinkedList;
import ua.edu.ucu.collections.ImmutableList;
import ua.edu.ucu.collections.MyQueue;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

public class Main {
    public static void main(String[] args) {
//        PrefixMatches pm = new PrefixMatches(new RWayTrie());
//        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
//        System.out.println(pm.wordsWithPrefix("abc"));
//        System.out.println(pm.wordsWithPrefix("abc", 3));

        MyQueue queue = new MyQueue();
        queue.enqueue("abc");
        System.out.println(queue);
    }
}
