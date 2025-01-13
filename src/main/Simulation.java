package main;

import main.Actions.InitialActions;
import main.Entities.*;
import main.utils.BFS;
import main.utils.Coordinates;
import main.utils.WordRender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Simulation {

    private final World world;
    private final BFS bfs;
    private final Random random = new Random();
    public static int step = 0;
    public Simulation(World world) {
        this.world = world;
        this.bfs = new BFS();
    }

    public void runSimulation() {
        WordRender.worldRender(world);

        while (true){

            System.out.println("Step: " + (step + 1) + "macos fix");

            List<Creature> creatures = getAllCreatures(world);

            for (Creature creature : creatures) {
                if (creature != null) {
                    // путь перед каждым движением
                    List<Coordinates> path = bfs.pathfinder(creature, world);
                    Coordinates nextStep = !path.isEmpty() ? path.get(1) : null;

                    if (nextStep != null && world.isCellAvailable(nextStep, creature)) {
                        creature.makeMove(world, path);
                    }
                    if(nextStep == null){
                        creature.makeRandomMove(world);
                    }
                }
            }

            WordRender.worldRender(world);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Creature> getAllCreatures(World world) {
        List<Creature> creatures = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : world.getEntities().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                creatures.add((Creature) entry.getValue());
            }
        }
        return creatures;
    }

    public static void main(String[] args) {
        InitialActions initialActions = new InitialActions();
        World world = initialActions.execute();
        Simulation simulation = new Simulation(world);
        simulation.runSimulation();
    }
}