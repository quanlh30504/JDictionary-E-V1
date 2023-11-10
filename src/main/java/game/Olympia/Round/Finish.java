package game.Olympia.Round;

import game.Olympia.UserInfo.Player;

public class Finish extends Player {
    @Override
    public int correctAnsPlus(int score) {
        return score;
    }

    @Override
    public int incorrectAnsMinus(int score) {
        return score;
    }

    public void scorePick() {

    }

    public boolean starOfHope() {
        return true;
    }
}
