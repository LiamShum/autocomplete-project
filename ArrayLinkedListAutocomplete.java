
import java.util.List;

public class ArrayLinkedListAutocomplete {

    // Node class
    class Node {

        String word;
        int frequency;
        Node next;
    }

    Node[] buckets = new Node[26];

    // methods

    // Add a new word with frequency 1 or increment an existing frequency by 1
    void addWord(String word) {

    }

    // Return the frequency of a valid word, or 0 if it is absent
    int getFrequency(String word) {

    }

    // Return whether the valid word is stored
    boolean containsWord(String word) {

    }

    /**
     * Return up to limit matches, ranked by frequency and then alphabetically.
     * Return an empty list when no words match
     *
     * @param prefix
     * @param limit
     * @return
     */
    List<Suggestion> autocomplete(String prefix, int limit) {

    }

    /**
     * If the word exists, increment its frequency and return true. If absent,
     * return false without inserting it
     *
     * @param word
     * @return
     */
    boolean selectWord(String word) {

    }

    // Return the number of distinct words, not the sum of their frequencies
    int size() {

    }
}
