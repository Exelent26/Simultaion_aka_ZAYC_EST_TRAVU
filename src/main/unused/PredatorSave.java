/*
package main.Entities;

import main.World;
import utils.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator(Coordinates coordinates) {
        super(Herbivore.class, coordinates, 45);
    }

    @Override
    public void makeMove(World world, List<Coordinates> path) {
        if (path.isEmpty()) return;

        Coordinates nextStep = path.get(1);
        processInteraction(world, nextStep);

        // Если на следующей клетке нет жертвы, просто двигаемся
        if (!(world.getEntity(nextStep) instanceof Herbivore)) {
            makeStep(world, nextStep);
        }
    }

    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity instanceof Herbivore) {
            world.removeEntity(target); // Атакуем и убиваем травоядное
        }
    }
}
*/
