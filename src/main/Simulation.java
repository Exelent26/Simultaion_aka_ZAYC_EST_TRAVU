package main;

import main.Actions.MarkedEntitiesRemoverAction;
import main.Actions.InitialActions;
import main.Actions.SimulationAction;
import main.utils.InputHandler;
import main.utils.WorldRender;
import main.Actions.*;

import java.util.Arrays;
import java.util.List;

public class Simulation extends Thread {

    public  volatile boolean isEnded = false;
    public  volatile boolean isPaused = false;


    private int stepCount = 0;
    private final World world;
    private final InitialActions initialActions;
    private final WorldRender worldRender;

    public Simulation(World world, InitialActions initialActions, WorldRender worldRender) {
        this.world = world;
        this.initialActions = initialActions;
        this.worldRender = worldRender;

    }

    public  World getWorld() {
        return world;
    }


    public void nextStep(World world)  {
        System.out.println();
        System.out.println("Step: " + (stepCount++));

        List<Action> actions = Arrays.asList(new SimulationAction(), new MarkedEntitiesRemoverAction(world));
        for (Action action : actions) {
            action.execute(world);
        }

        worldRender.render(world);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        initialActions.execute(world);
        stepCount++;
        worldRender.render(world);

        while (!isEnded) {
            if(isPaused) {
                continue;
            }
            nextStep(world);
            if (isEnded) {
                break;
            }
        }
    }

    public  void pauseSim() {
        this.isPaused = true;
    }

    public  void resumeSim() {
        this.isPaused = false;

    }
    public void shoutDown() {
        this.isEnded = true;
        this.isPaused = false;
    }

    public static void main(String[] args) {

        Simulation simulation = new Simulation(new World(15, 15), new InitialActions(), new WorldRender());
        simulation.start();

        InputHandler inputHandler = new InputHandler(simulation);
        inputHandler.start();

    }

}



