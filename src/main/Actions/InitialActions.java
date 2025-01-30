package main.Actions;

import main.Configs.EntitySpawnConfig;
import main.World;

public class InitialActions implements Action {



    public void execute(World world) {

        for(EntitySpawnConfig entitySpawnConfig : EntitySpawnConfig.values()) {
            SpawnAction spawnAction = new SpawnAction(entitySpawnConfig);
            spawnAction.execute(world);
        }
    }

    //@Override
    /*public void execute() {
    }

    private void createEntities(World world) {
        for (int i = 0; i < SimulationConfig.HERBIVORE_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            Herbivore herbivore = new Herbivore();
            world.addEntity(herbivore, temp);
        }

        for (int i = 0; i < SimulationConfig.GRASS_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            Grass grass = new Grass();
            world.addEntity(grass, temp);
        }
        for (int i = 0; i < SimulationConfig.TREE_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            world.addEntity(new Tree(), temp);
        }
        for (int i = 0; i < SimulationConfig.ROCK_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            world.addEntity(new Rock(), temp);
        }
        for (int i = 0; i < SimulationConfig.PREDATOR_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            Predator predator = new Predator();
            world.addEntity(predator, temp);
        }
    }
*/


}