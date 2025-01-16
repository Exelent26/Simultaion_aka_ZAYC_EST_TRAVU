package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Herbivore extends Creature {

    private int prepareToEatCounter = 0;

    public Herbivore(Coordinates coordinates) {
        super(Grass.class, coordinates, 20, 1);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }

        System.out.println("Moving entity " + this + " from " + this.coordinates + " to " + nextStep);
        makeStep(world, nextStep);

        //processInteraction(world, nextStep); возможно лишний метод дублирование makeStep
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity != null) {
            List<Coordinates> adjacentCoordinates = world.getAvailableMoves(target, this);
            for (Coordinates adjacentCoordinate : adjacentCoordinates) {
                Entity adjacentEntity = world.getEntity(adjacentCoordinate);
                if (adjacentEntity instanceof Grass) {
                    if (prepareToEatCounter == 0) {
                        prepareToEatCounter++;
                        return;
                    } else if (prepareToEatCounter == 1) {
                        world.removeEntity(adjacentCoordinate);
                        prepareToEatCounter = 0;
                        return;
                    }
                }
            }
            prepareToEatCounter = 0;
        }
    }
}