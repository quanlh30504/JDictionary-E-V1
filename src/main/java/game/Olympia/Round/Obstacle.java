package game.Olympia.Round;

import game.Olympia.UserInfo.Player;

public class Obstacle extends Player {

    @Override
    public void correctAnsPlus(int score) {
        score += 10;
        setObstacleScore(score);
    }

    @Override
    public void incorrectAnsMinus(int score) {

    }

    public void finalAnswer(int pickedRow, int score) {

        switch (pickedRow) {
            case 1:
                score += 40;
                setObstacleScore(score);
            case 2:
                score += 30;
                setObstacleScore(score);
            case 3:
                score += 20;
                setObstacleScore(score);
            case 4:
                score += 10;
                setObstacleScore(score);
        }
    }

}
