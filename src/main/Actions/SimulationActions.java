package main.Actions;

import main.Entities.Creature;
import main.Entities.Herbivore;
import main.World;

public class SimulationActions implements Action {
    @Override
    public World execute() {
        throw new UnsupportedOperationException("Use execute(World world) instead.");
    }

    @Override
    public void execute(World world) {
        /*for (Creature creature : world.getCreatures()) {
            creature.makeMove(world);
        }*/
        //handleDeaths(world);
        handleReproduction(world);
    }

    @Override
    public boolean isSimulationOver(World world) {
        return true;//world.getEntitiesOfType(Herbivore.class).isEmpty() ||
                //world.getEntitiesOfType(Grass.class).isEmpty();
    }

    /*private void handleDeaths(World world) {
        for (Creature creature : world.getCreatures()) {
            if (creature.getHealth() <= 0) {
                world.removeEntity(creature.getCoordinates());
            }
        }
    }*/

    private void handleReproduction(World world) {
        // Logic for adding new entities based on world state
    }
}