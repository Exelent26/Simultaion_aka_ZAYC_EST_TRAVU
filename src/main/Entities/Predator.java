package main;

public class Predator extends Creature{
    public Predator(Coordinates coordinates) {
        super(Herbivore.class, coordinates); // Травоядные едят траву
    }

}
