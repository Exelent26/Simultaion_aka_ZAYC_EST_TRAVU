package main.utils;

public class StepCounter {
    private int steps = 0;

    public void increaseStepCount() {
        steps++;

    }
    private int getSteps() {
        return steps;
    }
    public void printStepCount() {
        System.out.printf("Step: %d",getSteps());
    }
}
