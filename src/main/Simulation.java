package main;

import main.Actions.DeadCreaturesRemoverAction;
import main.Actions.InitialActions;
import main.Actions.SimulationActions;
import main.Entities.*;
import main.utils.BFS;
import main.utils.Coordinates;
import main.utils.WorldRender;
import main.Actions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {


    public  int stepCount = 0;

    public Simulation() {

    }

    public void runSimulation(World world) {

        WorldRender.worldRender(world);

        while (true) {

            System.out.println();
            System.out.println("Step: " + (stepCount++));
            List<Action> actions = Arrays.asList(new SimulationActions(), new DeadCreaturesRemoverAction());
            for (Action action : actions) {
                action.execute(world);
            }

            WorldRender.worldRender(world);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*List<Creature> creatures = world.getAllCreatures();
            BFS bfs = new BFS();

            for (Creature creature : creatures) {
                if (world.containsEntity(creature) && creature.alive) {
                    creature.increaseHunger();

                    if (!creature.isDead()) {
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
*/
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