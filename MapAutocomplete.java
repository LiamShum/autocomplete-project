
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAutocomplete {

    Map<String, Integer> map = new HashMap<>();

    /**
     * Add a new word with frequency 1 or increment an existing frequency by 1
     * trims surrounding white space converts to lowercase contains letters
     * through a and z only, rejects reset with IllegalArgumentException
     */
    void addWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word is null.");
        }
        // clean all whitespace and convert to lower
        String cleanedWord = word.replaceAll("\\s+", "").toLowerCase();
        boolean onlyLetters = cleanedWord.matches("[a-z]+");
        if (cleanedWord.isEmpty()) {
            throw new IllegalArgumentException("Word is empty.");
        }
        if (!onlyLetters) {
            throw new IllegalArgumentException("Word contains illegal characters.");
        }

        if (containsWord(cleanedWord)) {
            map.put(cleanedWord, map.get(cleanedWord) + 1);
        } else {
            map.put(cleanedWord, 1);
        }
    }

    // Return the frequency of a valid word, or 0 if it is absent
    int getFrequency(String word) {
        String cleanWord = word.replaceAll("\\s+", "").toLowerCase();
        if (containsWord(cleanWord)) {
            return map.get(cleanWord);
        } else {
            return 0;
        }
    }

    // Return whether the valid word is stored
    boolean containsWord(String word) {
        if (word == null) {
            return false;
        }
        String cleanWord = word.replaceAll("\\s+", "").toLowerCase();
        return map.containsKey(cleanWord);
    }

    // Return up to limit matches, ranked by frequency and then alphabetically.
    // Return an empty list when no words match
    List<Suggestion> autocomplete(String prefix, int limit) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix is null.");
        }

        String cleanPrefix = prefix.replaceAll("\\s+", "").toLowerCase();
        List<Suggestion> matches = new ArrayList<>();

        if (limit <= 0) {
            return matches;
        }
        // collects words that begins with the prefix and adds them to matches
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            if (word.startsWith(cleanPrefix)) {
                matches.add(new Suggestion(word, frequency));
            }
        }

        matches.sort((a, b) -> {
            if (a.frequency != b.frequency) {
                return Integer.compare(b.frequency, a.frequency);
            }

            return a.word.compareTo(b.word);
        });

        if (matches.size() > limit) {
            return new ArrayList<>(matches.subList(0, limit));
        }
        return matches;
    }

    /**
     * If the word exists, increment its frequency and return true. If absent,
     * return false without inserting it
     *
     * @param word
     * @return
     */
    boolean selectWord(String word) {
        String cleanWord = word.replaceAll("\\s+", "").toLowerCase();
        if (containsWord(cleanWord)) {
            map.put(cleanWord, map.get(cleanWord) + 1);
            return true;
        }
        return false;
    }

    // Return the number of distinct words, not the sum of their frequencies
    int size() {
        return map.size();
    }
}
