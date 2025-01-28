package main;

import main.Actions.InitialActions;
import main.Entities.*;
import main.utils.BFS;
import main.utils.Coordinates;
import main.utils.WorldRender;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {


    private final BFS bfs;
    private final Random random = new Random();
    public static int stepCount = 0;

    public Simulation() {

        this.bfs = new BFS();
    }

    public void runSimulation(World world) {

        WorldRender.worldRender(world);

        while (true) {
            System.out.println();
            System.out.println("Step: " + (stepCount++));

            List<Creature> creatures = world.getAllCreatures();


            for (Creature creature : creatures) {

                if (world.containsEntity(creature) && creature.alive) {
                    creature.increaseHunger(); // Увеличиваем голод перед каждым ходом

                    if (!creature.isDead()) { // Если существо живо, оно может двигаться
                        for (int i = 0; i < creature.getSpeed(); i++) {
                            List<Coordinates> path = bfs.getPath(world, world.getCoordinates(creature), creature.getFoodType());
                            if (!path.isEmpty()) {
                                Coordinates nextStep = path.get(1);
                                if (nextStep != null && world.isCellAvailable(nextStep, creature)) {
                                    creature.makeMove(world, nextStep);
                                }
                            }else {
                                creature.makeRandomMove(world);
                            }
                        }
                    }
                }
            }

            world.cleanupDeadEntities();

            WorldRender.worldRender(world);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        World world = new World();
        InitialActions initialActions = new InitialActions();
        initialActions.execute(world);
        Simulation simulation = new Simulation();
        simulation.runSimulation(world);
    }
}