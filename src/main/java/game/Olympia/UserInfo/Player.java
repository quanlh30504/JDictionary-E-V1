package game.Olympia.UserInfo;

public abstract class Player extends User {

    protected int startingScore;
    protected int obstacleScore;
    protected int accelerationScore;
    protected int finishScore;
    public static int overallScore;

    public Player(String password, String userName, int startingScore, int obstacleScore, int accelerationScore, int finishScore, int overallScore) {
        super(password, userName);
        this.startingScore = startingScore;
        this.obstacleScore = obstacleScore;
        this.accelerationScore = accelerationScore;
        this.finishScore = finishScore;
        this.overallScore = overallScore;
    }

    public Player() {
    }

    public int getStartingScore() {
        return startingScore;
    }

    public void setStartingScore(int startingScore) {
        this.startingScore = startingScore;
    }

    public int getObstacleScore() {
        return obstacleScore;
    }

    public void setObstacleScore(int obstacleScore) {
        this.obstacleScore = obstacleScore;
    }

    public int getAccelerationScore() {
        return accelerationScore;
    }

    public void setAccelerationScore(int accelerationScore) {
        this.accelerationScore = accelerationScore;
    }

    public int getFinishScore() {
        return finishScore;
    }

    public void setFinishScore(int finishScore) {
        this.finishScore = finishScore;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public abstract void correctAnsPlus(int score);
    public abstract void incorrectAnsMinus(int score);
}
