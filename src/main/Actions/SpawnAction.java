package main.Actions;

import main.Entities.Entity;
import main.EntityQuantityCalculator;
import main.EntitySpawnConfig;
import main.World;
import main.utils.Coordinates;

public class SpawnAction implements Action {
    private final EntitySpawnConfig spawnConfig;

    public SpawnAction(EntitySpawnConfig spawnConfig) {
        this.spawnConfig = spawnConfig;
    }

    @Override
    public void execute(World world) {
        int worldSquare = world.getWidth() * world.getHight();
        int quantity = EntityQuantityCalculator.calculateQuantity(
                worldSquare,
                spawnConfig.getPercentOfMap(),
                spawnConfig.getMinimumOfEntityType());
        for (int i = 0; i < quantity; i++) {
            Entity entityToWorld = spawnConfig.getSupplier().get();
            Coordinates coordinatesForSpawn = world.makeRandomPositionForEntity();
            world.addEntity(entityToWorld, coordinatesForSpawn);
        }



    }

}
