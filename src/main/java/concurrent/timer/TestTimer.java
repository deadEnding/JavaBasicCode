package concurrent.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: yuhui
 * @date: 03/07/2017
 */


public class TestTimer {

    public void start() {
        Timer timer = new Timer("HHHH", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("11111111 " + TestTimer.this);
            }
        }, 0, 2000);
    }

    public static void main(String[] args) throws InterruptedException {
        new TestTimer().start();
        for (int i = 0; i < 30; i++) {
            System.out.println("xxxxxxxxx");
            Thread.sleep(1000);
        }
    }
}
