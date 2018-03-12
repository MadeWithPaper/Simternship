package simternship.simternship;

import android.widget.Toast;

import java.util.List;

import java.util.Observable;

/**
 * Created by joel on 3/9/18.
 */

public class CareerFairController extends Observable {
    private UITimer timer;
    private List<Company> companies;
    private CareerFairFactory careerFairFactory;
    private CareerFair careerFair;
    private GameState gameState;
    private UITimer.TimerStatus timerStatus;

    enum State {
        BEGIN, FALL, FALL_WINTER, WINTER, WINTER_SPRING, SPRING, SPRING_END, END
    }

    private State state;


    public static final int MIN = 60 * 1000;
    public static final int TIMEBETWEENFAIRS = 10 * MIN;
    public static final int TIMEOFFAIR = 15 * MIN;

    public CareerFairController(UITimer timer,
                                GameState gameState,
                                List<Company> companies,
                                CareerFairFactory careerFairFactory) {
        this.timer = timer;
        this.companies = companies;
        this.careerFairFactory = careerFairFactory;
        this.state = State.BEGIN;
        this.gameState = gameState;
    }

    public void start() {
        this.scheduleNext();
    }

    public void stop() {
        this.timer.stop();
    }

    public CareerFair getCareerFair() {
        return this.careerFair;
    }

    public long timeRemaining() {
        if (timerStatus != null)
            return timerStatus.remainingTime();
        else
            return 0;
    }

    private void scheduleNext() {
        int time = 0;
        if (state == State.FALL || state == State.WINTER || state == State.SPRING) {
            time = TIMEOFFAIR;
        }
        if (state == State.BEGIN || state == State.FALL_WINTER
                || state == State.WINTER_SPRING || state == State.SPRING_END) {
            time = TIMEBETWEENFAIRS;
        }

        timerStatus = timer.runAfter(time, new Runnable() {
            public void run() {
                CareerFairController.this.startNext();
            }
        });
    }

    private void startNext() {
        switch (state) {
            case BEGIN:
                state = State.FALL;
                startFair();
                break;
            case FALL:
                state = State.FALL_WINTER;
                endFair();
                break;
            case FALL_WINTER:
                state = State.WINTER;
                startFair();
                break;
            case WINTER:
                state = State.WINTER_SPRING;
                endFair();
                break;
            case WINTER_SPRING:
                state = State.SPRING;
                startFair();
                break;
            case SPRING:
                state = State.SPRING_END;
                endFair();
                break;
            case SPRING_END:
                state = State.END;
                endGame();
                break;
            default:
                break;
        }
    }

    private void startFair() {
        careerFair = careerFairFactory.createCareerFair(companies);
        scheduleNext();
        gameState.gotUpdated();
        gameState.showToast("Career Fair Started!", Toast.LENGTH_SHORT);
    }

    private void endFair() {
        careerFair = null;
        scheduleNext();
        gameState.computeInterviews();
        gameState.gotUpdated();
        gameState.showToast("Career Fair Ended! Good luck with interviews!",
                Toast.LENGTH_SHORT);
    }

    private void endGame() {
        gameState.forceChooseOffer();
    }
}
