import java.util.ArrayList;

/**
 * class tu dien
 */
public class Dictionary {
    private ArrayList<Word> words;

    /**
     * constructor
     */
    Dictionary() {
        words = new ArrayList<>();
    }

    /**
     * @param word word
     */
    public void addWord(Word word){
        words.add(word);
    }

    /**
     * @param i position
     * @param word word
     */
    public void addWord(int i, Word word){
        words.add(i,word);
    }

    /**
     * @param i position
     * @return word
     */
    public Word getWord(int i){
        return words.get(i);
    }

    /**
     * remove word at position i
     * @param i position
     */
    public void removeWord(int i){
        words.remove(i);
    }

    /**
     * @return number of word
     */
    public int numOfWord(){
        return words.size();
    }

    /**
     * @return ArrayList<word>
     */
    public ArrayList<Word> getWords(){
        return words;
    }
}
