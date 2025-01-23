package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator(Coordinates coordinates) {
        super(Herbivore.class, 45, 2);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep);
        if (entity instanceof Herbivore) {
            System.out.println("Predator " + this + " eats Herbivore at " + nextStep);
            interactWithEntity(world, entity, nextStep);
        }else {
            Coordinates currentCoordinates = world.getCoordinates(this);
            System.out.println("Moving entity " + this + " from " + currentCoordinates + " to " + nextStep);
            makeStep(world, nextStep);
        }
    }


    private void attack(World world, Coordinates nextStep) {
        Entity target = world.getEntity(nextStep);
        if (target instanceof Herbivore) {
            ((Herbivore) target).health -= 20;
            System.out.println("Predator " + this + " attacks Herbivore at " + nextStep);

        }
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        Entity target = world.getEntity(targetCoordinates);
        if(target instanceof Herbivore &&((Herbivore) target).health>0) {
            this.attack(world, targetCoordinates);
        }
        else if(target instanceof Herbivore &&((Herbivore) target).health==0){
            world.removeEntity(targetCoordinates);
            System.out.println("Predator " + this + " kill "+ target + targetCoordinates);
        }
    }
}
