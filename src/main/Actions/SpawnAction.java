package main.Actions;

import main.Entities.Entity;
import main.utils.EntityQuantityCalculator;
import main.Configs.EntitySpawnConfig;
import main.World;
import main.utils.Coordinates;

public class SpawnAction implements Action {
    private final EntitySpawnConfig spawnConfig;

    public SpawnAction(EntitySpawnConfig spawnConfig) {
        this.spawnConfig = spawnConfig;
    }

    @Override
    public void execute(World world) {
        int worldSquare = world.getWorldSquare();
        int quantity = EntityQuantityCalculator.calculateQuantity(
                worldSquare,
                spawnConfig.getPercentOfMap(),
                spawnConfig.getMinimumOfEntityType());
        for (int i = 0; i < quantity; i++) {
            Entity entityToWorld = spawnConfig.getSupplier().get();
            Coordinates coordinatesForSpawn = world.makePositionForNewEntity();
            world.addEntity(entityToWorld, coordinatesForSpawn);
        }
    }

}
