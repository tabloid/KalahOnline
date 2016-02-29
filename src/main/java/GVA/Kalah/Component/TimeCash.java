package GVA.Kalah.Component;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Vsevolod on 26.02.2016.
 */
@Component
public class TimeCash {
    private Random random = new Random();
    private long liveTime = 1000 * 60 * 5;
    private final Map<Long, KalahGame> map =
            Collections.synchronizedMap(new HashMap<Long, KalahGame>());

    public TimeCash() {
        Thread thread = new Thread(new CashCleaner());
        thread.setDaemon(true);
        thread.start();
    }

    public long addGame(KalahGame kalahGame) {
        long id;
        synchronized (map) {
            id = random.nextInt(Integer.MAX_VALUE) + (long)(0.1 * System.currentTimeMillis());
            map.put(id, kalahGame);
        }
        return id;
    }

    public List<Long> getAvailableGames() {
        List<Long> list = new ArrayList<>();
        synchronized (map) {
            Iterator<Map.Entry<Long, KalahGame>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, KalahGame> entry = iterator.next();
                long id = entry.getKey();
                KalahGame kalahGame = entry.getValue();
                if (!kalahGame.containsTwoPlayers())
                    list.add(id);
            }
        }
        return list;
    }

    public boolean containsGame(long id) {
        return map.containsKey(id);
    }

    public KalahGame getGame(long id) {
        KalahGame kalahGame = map.get(id);
        kalahGame.setLastTimeModified(System.currentTimeMillis());
        System.out.println("modify lastTime " + id);
        return kalahGame;
    }

    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }

    private class CashCleaner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                synchronized (map) {
                    Iterator<Map.Entry<Long, KalahGame>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Long, KalahGame> entry = iterator.next();
                        KalahGame kalahGame = entry.getValue();
                        if (System.currentTimeMillis() - kalahGame.getLastTimeModified() > liveTime) {
                            System.out.println(entry.getKey() + " removed");
                            iterator.remove();
                        }
                    }
                }

            }
        }
    }
}
