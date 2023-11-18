package CMDDictionary;

public class Word {
    public String wordFound;
    public String wordExplaination;

    public Word(String wordFound,String wordExplaination) {
        this.wordFound = wordFound;
        this.wordExplaination = wordExplaination;
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