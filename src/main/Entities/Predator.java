package main.Entities;

import main.World;
import main.utils.Coordinates;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator() {
        super(Herbivore.class, 25, 2, 10);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep).orElse(null);
        if (entity instanceof Herbivore) {
            interactWithEntity(world, entity, nextStep);
        } else {
            makeStep(world, nextStep);
        }
    }


    private void attack(Herbivore herbivore) {
        herbivore.setHealth(herbivore.getHealth()-attackPower);

    }


    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        if (entity instanceof Herbivore herbivore) {
            if (herbivore.getHealth() > 0) {
                attack(herbivore);
                if (herbivore.isDead()) {
                    eatTarget(20, 15);
                }
            }
        }
    }
}
