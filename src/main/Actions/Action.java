package main.Actions;

import main.World;

interface Action {
    World execute();

    void execute(World world);

    default boolean isSimulationOver(World world) {
        return false;
    }
}
