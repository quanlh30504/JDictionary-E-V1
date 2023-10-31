package dictionary;

public class Word {
    public static String wordFound;
    public static String wordExplaination;

    public Word(String wordFound,String wordExplaination) {
        Word.wordFound = wordFound;
        Word.wordExplaination = wordExplaination;
    }

    public String getWordFound() {
        return wordFound;
    }

    public void setWordFound(String wordFound) {
        this.wordFound = wordFound;
    }

    public String getWordExplaination() {
        return wordExplaination;
    }

    public void setWordExplaination(String wordExplaination) {
        this.wordExplaination = wordExplaination;
    }

}
