package main;

import main.Actions.DeadCreaturesRemoverAction;
import main.Actions.InitialActions;
import main.Actions.SimulationActions;
import main.utils.WorldRender;
import main.Actions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Simulation extends Thread {

    public volatile boolean isStarted = true;
    public volatile boolean isPaused = false;


    private int stepCount = 0;
    private final World world;
    private final InitialActions initialActions;
    private final WorldRender worldRender;

    public Simulation(World world, InitialActions initialActions, WorldRender worldRender) {
        this.world = world;
        this.initialActions = initialActions;
        this.worldRender = worldRender;

    }

    public World getWorld() {
        return world;
    }


    public void runSimulation() {
        initialActions.execute(world);
        stepCount++;
        worldRender.render(world);

        while (isStarted) {
            synchronized (this) {
                while (isPaused) { // Пока пауза, поток ждёт
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            nextStep(world);
        }
    }

    private synchronized void nextStep(World world) {
        System.out.println();
        System.out.println("Step: " + (stepCount++));

        List<Action> actions = Arrays.asList(new SimulationActions(), new DeadCreaturesRemoverAction());
        for (Action action : actions) {
            action.execute(world);
        }

        worldRender.render(world);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runSimulation();
    }

    public synchronized void makeOneStep(World world) {
        nextStep(world);
        isPaused = false; // Разрешаем выполнение одного шага
        notify(); // Будим поток
    }

    public synchronized void shoutDown() {
        this.isStarted = false;
        this.isPaused = false; // Если поток на паузе, снимаем её
        notify(); // Будим поток, если он ждал
    }

    public synchronized void pauseSim() {
        this.isPaused = true;
    }

    public synchronized void resumeSim() {
        this.isPaused = false;
        notify(); // Будим поток, чтобы он продолжил работать
    }

    public static void main(String[] args) throws InterruptedException {

        Simulation simulation = new Simulation(new World(15, 15), new InitialActions(), new WorldRender());
        simulation.start();
        Thread input = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите команду (1 - стоп, 2 - шаг, 3 - пауза, 4 - продолжить):");
                int i = Integer.parseInt(scanner.nextLine());

                if (i == 1) {
                    simulation.shoutDown();
                    break;
                } else if (i == 2) {
                    simulation.nextStep(simulation.getWorld());
                } else if (i == 3) {
                    simulation.pauseSim();
                } else if (i == 4) {
                    simulation.resumeSim();
                } else {
                    System.out.println("Неизвестная команда.");
                }
            }

            System.out.println("Симуляция завершена.");
        });

        input.start();
    }

}



