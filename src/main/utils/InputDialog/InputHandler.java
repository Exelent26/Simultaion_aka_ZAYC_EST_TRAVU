package main.utils.InputDialog;

import main.Simulation;

import java.util.List;

public class InputHandler extends Thread {
    private final Simulation simulation;
    private final InputSimulationControlDialog inputSimulationControlDialog;

    public InputHandler(Simulation simulation) {
        this.simulation = simulation;
        this.inputSimulationControlDialog = new InputSimulationControlDialog(
                """
                Нажмите клавишу для управления симуляцией
                1 - поставить симуляцию на паузу
                2 - продолжить симуляцию
                3 - сделать шаг в симуляции
                4 - завершить симуляцию""",
                "неверный ввод",
                List.of(1, 2, 3, 4)
        );
    }

    @Override
    public void run() {
        simulationStatusRedactor();
    }

    private void simulationStatusRedactor()  {
        while (!simulation.isEnded) {
                int modeSelector = inputSimulationControlDialog.input();
                switch (modeSelector) {
                    case 1 -> simulation.pauseSim();
                    case 2 -> simulation.resumeSim();
                    case 3 -> {
                        if (!simulation.isEnded) simulation.nextStep(simulation.getWorld());
                    }
                    case 4 -> simulation.shoutDown();

            }
        }
    }
}