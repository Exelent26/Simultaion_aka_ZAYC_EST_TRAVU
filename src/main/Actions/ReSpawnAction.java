package main.Actions;

import main.Configs.CreatureAndGrassRespawnConfig;
import main.Entities.Grass;
import main.EntityQuantityCalculator;
import main.World;
import main.utils.Coordinates;

import java.util.Random;

public class ReSpawnAction implements Action {
    private final CreatureAndGrassRespawnConfig config;

    public ReSpawnAction(CreatureAndGrassRespawnConfig config) {
        this.config = config;
    }


    @Override
    public void execute(World world) {
        int worldSquare = world.getWorldSquare();
        Random rand = new Random();
        if(EntityQuantityCalculator.calculatePartOfEntityInMap(worldSquare,world, config.getEntityClass())<config.getMinPercentOfMapForRespawn()){
            int entityTorRespawnQuantity = rand.nextInt(config.getMinimumEntityNumberToRespawn(),config.getMaximumEntityNumberToRespawn());
            for (int i = 1; i <= entityTorRespawnQuantity ; i++) {
                Coordinates temp = world.makeRandomPositionForEntity();
                world.addEntity(config.getSupplier().get(),temp);
                System.out.println("Added new entity "+config.getEntityClass()+" " + temp +" in quantity "+entityTorRespawnQuantity);

            }
        }

    }
}
