package GVA.Kalah.Component;

import GVA.Kalah.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by v.herasymenko on 2/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
public class TimeCashTest {

    @Test
    public void concurrentAddGameMethodMustAddDifferentNewGames() throws InterruptedException {
        final TimeCash timeCash = new TimeCash();
        Random random = new Random();
        int n = random.nextInt(100);
        System.out.println("Testing for " + n + " concurrent insertions");
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    KalahGame kalahGame = new KalahGame();
                    timeCash.addGame(kalahGame);
                }
            });
            t.start();
        }
        Thread.sleep(1000);
        assertEquals(n, timeCash.getAvailableGames().size());
    }

    @Test
    public void cashCleanerMustCleanCashAfterLiveTime() throws InterruptedException{
        TimeCash timeCash = new TimeCash();
        long liveTime = 10 * 1000;
        timeCash.setLiveTime(liveTime);
        for (int i = 0; i < 10; i++)
            timeCash.addGame(new KalahGame());
        Thread.sleep(liveTime + 1000);
        assertEquals(0, timeCash.getAvailableGames().size());
    }
}
