package main.Entities;

import main.World;
import main.utils.Coordinates;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator() {
        super(Herbivore.class, 25, 2,10);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep);
        if (entity instanceof Herbivore) {
            interactWithEntity(world, entity, nextStep);
        } else {
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
        if (entity instanceof Herbivore herbivore) {
            if (herbivore.health > 0) {

                herbivore.health -= attackPower;
                System.out.println("Predator " + this + " attacks Herbivore at " + targetCoordinates);


                if (herbivore.isDead()) {
                    System.out.println("Herbivore at " + targetCoordinates + " is now dead. Killed by " + this);
                    eatTarget(20, 15);
                }
            }
        }
    }
}
