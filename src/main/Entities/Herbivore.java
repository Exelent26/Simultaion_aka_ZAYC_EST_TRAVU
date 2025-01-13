package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Herbivore extends Creature {
    private int prepareToEatCounter = 0;

    public Herbivore(Coordinates coordinates) {
        super(Grass.class, coordinates, 20);
    }

    @Override
    public void makeMove(World world, List<Coordinates> path) {
        if (path == null || path.size() <= 1) {
            makeRandomMove(world);
            return;
        }

        Coordinates nextStep = path.get(1);
        System.out.println("Moving entity " + this + " from " + this.coordinates + " to " + nextStep);
        makeStep(world, nextStep);

        processInteraction(world, nextStep);
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity == null) {
            List<Coordinates> adjacentCoordinates = world.getAvailableMoves(target, this);
            for (Coordinates adjacentCoordinate : adjacentCoordinates) {
                Entity adjacentEntity = world.getEntity(adjacentCoordinate);
                if (adjacentEntity instanceof Grass) {
                    if (prepareToEatCounter == 0) {
                        prepareToEatCounter = 1;
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