package game.Olympia.Round;

import game.Olympia.UserInfo.Player;

public class Starting extends Player {

    //private Player player = new Player();

    @Override
    public void correctAnsPlus(int score) {
         score += 10;
         setAccelerationScore(score);
    }

    @Override
    public void incorrectAnsMinus(int score) {
        score -= 5;
        setAccelerationScore(score);
    }

}
