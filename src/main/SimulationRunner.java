package main;

import main.Actions.InitialActions;
import main.utils.InputDialog.InputHandler;
import main.utils.WorldRender;


public class SimulationRunner {

    public static void main(String[] args) {

        Simulation simulation = new Simulation(new World(10, 10),
                new InitialActions(), new WorldRender());
        simulation.start();

        InputHandler inputHandler = new InputHandler(simulation);
        inputHandler.start();

    }
}
