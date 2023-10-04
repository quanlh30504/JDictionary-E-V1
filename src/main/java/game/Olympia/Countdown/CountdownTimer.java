package game.Olympia.Countdown;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

    public void Countdown(int seconds) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                if (remainingSeconds > 0) {
                    System.out.println("Thời gian còn lại: " + remainingSeconds + " giây");
                    remainingSeconds--;
                } else {
                    System.out.println("Hết cứu");
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        int seconds = 60;
        CountdownTimer countdownTimer = new CountdownTimer();
        countdownTimer.Countdown(seconds);
    }
}
