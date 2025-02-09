package main;

import main.Actions.MarkedEntitiesRemoverAction;
import main.Actions.InitialActions;
import main.Actions.SimulationAction;
import main.utils.StepCounter;
import main.utils.WorldRender;
import main.Actions.*;

import java.util.Arrays;
import java.util.List;

public class Simulation extends Thread {

    public  volatile boolean isEnded = false;
    public  volatile boolean isPaused = false;



    private final World world;
    private final InitialActions initialActions;
    private final WorldRender worldRender;
    private final StepCounter stepCounter = new StepCounter();

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
        stepCounter.increaseStepCount();
        stepCounter.printStepCount();

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


}



