package ua.edu.ucu.tries;

import ua.edu.ucu.collections.MyQueue;

// Implementation taken from Algorithms 4th edition by R.Sedgewick
public class RWayTrie implements Trie {

    private static int R = 256;
    private Node root = new Node();
    private int size = 0;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Object get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Object val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Object val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    private void collect(Node x, String pre,
                         MyQueue q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length())
            x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    @Override
    public void add(Tuple t) {
        root = this.put(this.root, t.term, t.weight, 0);
        size++;
    }

    @Override
    public boolean contains(String word) {
        Node searchedForNode = this.get(root, word, 0);
        return searchedForNode != null && searchedForNode.val != null;
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            root = delete(root, word, 0);
            size--;
            return true;
        }
        return false;

    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        MyQueue queue = new MyQueue();
        collect(get(root, s, 0), s, queue);
        return queue::iterator;
    }

    @Override
    public int size() {
        return size;
    }

}
