package game.Olympia.Round;

import game.Olympia.UserInfo.Player;

import java.util.Scanner;

public class Starting extends Player {

    //private Player player = new Player();

    public Starting(int startingScore) {
        this.startingScore = getStartingScore();
    }

    @Override
    public void correctAnsPlus(int score) {
         score += 10;
         setStartingScore(score);
    }

    @Override
    public void incorrectAnsMinus(int score) {
        score -= 5;
        setStartingScore(score);
    }

    public static void main(String[] args) {
        Starting starting = new Starting(0);
        Scanner scanner = new Scanner(System.in);
        int rowNumber;
        while (true) {
            rowNumber = scanner.nextInt();
            //obstacle.finalAnswer(rowNumber, obstacle.obstacleScore);
            starting.correctAnsPlus(starting.startingScore);
            System.out.println(starting.getStartingScore());
        }
    }
}
