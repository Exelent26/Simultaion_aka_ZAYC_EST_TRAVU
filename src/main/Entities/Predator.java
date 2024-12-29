package main.Entities;

import main.World;
import utils.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator(Coordinates coordinates) {
        super(Herbivore.class, coordinates, 45);
    }

    @Override
    public void makeMove(World world, List<Coordinates> path) {
        if (path == null || path.isEmpty()) {
            makeRandomMove(world); // Случайное перемещение если путь пуст
            return;
        }

        Coordinates nextStep = path.get(1); // Первый шаг на пути

        // Проверяем, что в клетке
        Entity entity = world.getEntity(nextStep);


        if (entity instanceof Herbivore) {
            System.out.println("Predator " + this + " eats Herbivore at " + nextStep);
            interactWithEntity(world, entity, nextStep);
        }


        System.out.println("Moving entity " + this + " from " + this.coordinates + " to " + nextStep);
        makeStep(world, nextStep);
    }
    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity instanceof Herbivore) {
                world.removeEntity(target);
            }
        }
    }
