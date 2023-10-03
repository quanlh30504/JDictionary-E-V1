package game.Olympia.Round;

import game.Olympia.UserInfo.Player;

import java.util.Scanner;

public class Obstacle extends Player {

    public Obstacle(int obstacleScore) {
        this.obstacleScore = getObstacleScore();
    }

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
            case 1 -> {
                score += 40;
                setObstacleScore(score);
            }
            case 2 -> {
                score += 30;
                setObstacleScore(score);
            }
            case 3 -> {
                score += 20;
                setObstacleScore(score);
            }
            case 4 -> {
                score += 10;
                setObstacleScore(score);
            }
            default -> {
            }
        }
    }

    public static void main(String[] args) {
        Obstacle obstacle = new Obstacle(0);
        Scanner scanner = new Scanner(System.in);
        int rowNumber;
        while (true) {
            rowNumber = scanner.nextInt();
            //obstacle.finalAnswer(rowNumber, obstacle.obstacleScore);
            obstacle.correctAnsPlus(obstacle.obstacleScore);
            System.out.println(obstacle.getObstacleScore());
        }
    }

}
