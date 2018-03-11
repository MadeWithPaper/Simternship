package simternship.simternship;

import java.util.Timer;
import android.app.Activity;
import java.util.TimerTask;

/**
 * Created by joel on 3/10/18.
 */

public class UITimer {
    private Timer timer;
    private final Activity activity;

    public UITimer(Activity activity) {
        this.activity = activity;
        this.timer = new Timer();
    }

    public TimerStatus runAfter(long ms, final Runnable runnable) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                UITimer.this.run(runnable);
            }
        }, ms);

        return new TimerStatus(ms);
    }

    private synchronized void run(Runnable runnable) {
        activity.runOnUiThread(runnable);
    }

    public static class TimerStatus {
        private long duration;
        private long started;

        TimerStatus(long ms) {
            this.duration = ms;
            this.started = System.currentTimeMillis();
        }

        public long remainingTime() {
            long now = System.currentTimeMillis();
            long elapsed = now - started;
            return Math.max(0, duration - elapsed);
        }
    }
}
