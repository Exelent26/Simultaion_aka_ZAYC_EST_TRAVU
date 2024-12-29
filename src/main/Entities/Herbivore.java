package main;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(Grass.class, coordinates); // Травоядные едят траву
    }
}