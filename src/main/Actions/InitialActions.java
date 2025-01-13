package main.Actions;

import main.Entities.*;
import main.SimulationConfig;
import main.World;
import main.utils.Coordinates;

public class InitialActions implements Action {


    @Override
    public World execute() {
        World world = new World();
        createEntities(world);
        return world;
    }

    @Override
    public void execute(World world) {
    }

    private void createEntities(World world) {
        for (int i = 0; i < SimulationConfig.HERBIVORE_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            Herbivore herbivore = new Herbivore(temp);
            world.addEntity(herbivore);
        }
        for (int i = 0; i < SimulationConfig.GRASS_QUANTITY; i++) {
            world.addEntity(new Grass(world.makeRandomPositionForEntity()));
        }
        for (int i = 0; i < SimulationConfig.TREE_QUANTITY; i++) {
            world.addEntity(new Tree(world.makeRandomPositionForEntity()));
        }
        for (int i = 0; i < SimulationConfig.ROCK_QUANTITY; i++) {
            world.addEntity(new Rock(world.makeRandomPositionForEntity()));
        }
        for (int i = 0; i < SimulationConfig.PREDATOR_QUANTITY; i++) {
            Coordinates temp = world.makeRandomPositionForEntity();
            Predator predator = new Predator(temp);
            world.addEntity(predator);
        }
    }



}