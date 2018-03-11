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


    public static final int min = 60 * 1000;
    public static final int timeBetweenFairs = 10 * min;
    public static final int timeOfFair = 15 * min;
    //public static final int timeBetweenFairs = 5000;
    //public static final int timeOfFair = 5000;

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
        if (state == State.FALL || state == State.WINTER || state == state.SPRING) {
            time = timeOfFair;
        }
        if (state == State.BEGIN || state == State.FALL_WINTER
                || state == State.WINTER_SPRING || state == State.SPRING_END) {
            time = timeBetweenFairs;
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
        gameState.showToast("Career Fair Ended!", Toast.LENGTH_SHORT);
        gameState.showToast("Good Luck With Interviews!", Toast.LENGTH_SHORT);
    }

    private void endGame() {
        gameState.forceChooseOffer();
    }
}
