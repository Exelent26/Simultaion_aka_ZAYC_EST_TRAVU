package main.Actions;


import main.Entities.Creature;
import main.Entities.Entity;
import main.Entities.Grass;
import main.World;
import main.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class MarkedEntitiesRemoverAction implements Action {
    private final World world;

    public MarkedEntitiesRemoverAction(World world) {
        this.world = world;
    }

    private final List<Coordinates> entitiesToRemove = new ArrayList<>();

    @Override
    public void execute(World world) {
        markEntitiesToRemove();
        cleanupMarketedEntities();
    }

    private void markCoordinateToRemove(Coordinates coordinates) {
        if (!entitiesToRemove.contains(coordinates)) {
            entitiesToRemove.add(coordinates);
        }
    }

    private void markEntitiesToRemove() {
        List<Entity> entities = world.getEntities();
        for (Entity entity : entities) {
            if (entity instanceof Creature creature && creature.isDead()) {
                markCoordinateToRemove(world.getCoordinates(creature));
            } else if (entity instanceof Grass grass && grass.isEaten()) {
                markCoordinateToRemove(world.getCoordinates(grass));
            }

        }
    }

    private void cleanupMarketedEntities() {
        for (Coordinates coordinates : entitiesToRemove) {
            world.removeEntity(coordinates);
        }
        entitiesToRemove.clear();
    }
}
