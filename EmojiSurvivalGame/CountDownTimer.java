package Assignment17;

import java.io.IOException;

public class CountDownTimer {

    public static void main(String[] args) {
        CountDownTimer countDownTimer = new CountDownTimer();
        countDownTimer.startTimer();
    }

    public void startTimer() {
        int timer = 3;
        while (timer >= 0){
            if (timer == 0){
                System.out.println("Let's Go !! " + Player.userName + "\n");
                speakMessage("Let's Go " + Player.userName);
            }
            else {
                System.out.print(timer + " ");
                speakMessage(String.valueOf(timer));
            }
            try {
                Thread.sleep(760);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            timer--;
        }
    }

    private void speakMessage(String message) {
        String[] command = {"espeak", message};
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
