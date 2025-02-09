package main.Entities;

import main.World;
import main.utils.Coordinates;

public class Herbivore extends Creature {

    private int prepareToEatCounter = 0;

    public Herbivore() {
        super(Grass.class, 25, 1,5);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if(!isAlive()){
            return;
        }
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep).orElse(null);
        if (entity instanceof Grass) {
            interactWithEntity(world, entity, nextStep);
        }else {
            makeStep(world, nextStep);
        }
    }



    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        if (entity instanceof Grass grass) {
            if (!grass.isEaten() && prepareToEatCounter == 1) {
                grass.markAsEaten();
                eatTarget(25, 15);
                prepareToEatCounter = 0;
            } else if (!grass.isEaten() && prepareToEatCounter == 0) {
                prepareToEatCounter++;
            } else {
                prepareToEatCounter = 0;
            }
        }
    }
}