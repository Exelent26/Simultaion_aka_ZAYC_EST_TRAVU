package main;

import main.Actions.InitialActions;
import main.Entities.*;
import main.utils.BFS;
import main.utils.Coordinates;
import main.utils.WorldRender;

import java.util.List;
import java.util.Random;

public class Simulation {

    private final World world;
    private final BFS bfs;
    private final Random random = new Random();
    public static int stepCount = 0;

    public Simulation(World world) {
        this.world = world;
        this.bfs = new BFS();
    }

    public void runSimulation() {
        WorldRender.worldRender(world);

        while (true){
            System.out.println();
            System.out.println("Step: " + (stepCount++));

            List<Creature> creatures = world.getAllCreatures();

            for (Creature creature : creatures) {
                if (creature != null) {

                    for (int i = 0; i < creature.getSpeed(); i++) {
                        Coordinates nextStep = bfs.nextStepFromPath(creature, world);
                        if (nextStep != null && world.isCellAvailable(nextStep, creature)) {
                            creature.makeMove(world, nextStep);
                        } else {
                            creature.makeRandomMove(world);
                        }
                    }
                }
            }

            WorldRender.worldRender(world);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    public static void main(String[] args) {
        InitialActions initialActions = new InitialActions();
        World world = initialActions.execute();
        Simulation simulation = new Simulation(world);
        simulation.runSimulation();
    }
}