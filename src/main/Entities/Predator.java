package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator(Coordinates coordinates) {
        super(Herbivore.class, coordinates, 45,2);
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
        } //будет ли есть хищник есть зайца без этой команды

        System.out.println("Moving entity " + this + " from " + this.coordinates + " to " + nextStep);

        makeStep(world, nextStep);
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity instanceof Herbivore) {
            world.removeEntity(target);

        }
    }
}
