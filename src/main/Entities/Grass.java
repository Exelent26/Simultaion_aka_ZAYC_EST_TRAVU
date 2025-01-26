package main.Entities;

public class Grass extends Entity {
    private boolean eaten = false;
    public boolean isEaten() {
        return eaten;
    }

    public void markAsEaten() {
        eaten = true;
    }
}

