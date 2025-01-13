/*
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
        if (path.isEmpty()) return;

        Coordinates nextStep = path.get(1);
        processInteraction(world, nextStep);

        // Если еды рядом нет, просто двигаемся
        if (!(world.getEntity(nextStep) instanceof Grass)) {
            makeStep(world, nextStep);
            prepareToEatCounter = 0; // Сбрасываем подготовку, если двигались
        }
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity instanceof Grass) {
            if (prepareToEatCounter == 0) {
                prepareToEatCounter++;
            } else if (prepareToEatCounter == 1) {
                world.removeEntity(target); // Поедаем траву
                prepareToEatCounter = 0;
                System.out.println("Herbivore съел траву на " + target);
            }
        }
    }
}
*/
