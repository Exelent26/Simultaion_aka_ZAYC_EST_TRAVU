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
        handleReproduction(world);
    }

    @Override
    public boolean isSimulationOver(World world) {
        return true;
    }



    private void handleReproduction(World world) {
        // логика для добавления новых сущностей, зависит от состояния мира
    }
}