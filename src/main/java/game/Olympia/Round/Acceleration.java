package game.Olympia.Round;

import game.Olympia.UserInfo.Player;
import javafx.scene.image.ImageView;

public class Acceleration extends Player {
    @Override
    public int correctAnsPlus(int score) {
        return 0;
    }

    @Override
    public int incorrectAnsMinus(int score) {
        return score;
    }

    public void setImageForPlaying(ImageView image) {
        //https://youtu.be/2c4NR9N933Y?si=srWEeksgYbK7OVNX
    }

    public int plus40(int score) {
        return score + 40;
    }

    public int plus30(int score) {
        return score + 30;
    }

    public int plus20(int score) {
        return score + 20;
    }

    public int plus10(int score) {
        return score + 10;
    }
}

