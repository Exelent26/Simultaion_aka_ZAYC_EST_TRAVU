package main.Entities;

import main.World;
import utils.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class Creature extends Entity {
    protected final Class<?> foodType;
    protected int health;

    public Creature(Class<?> foodType, Coordinates coordinates, int health) {
        super(coordinates);
        this.foodType = foodType;
        this.health = health;
    }

    public Class<?> getFoodType() {
        return foodType;
    }

    public boolean canInteract(Entity entity) {
        return foodType.isInstance(entity);
    }

    public void makeRandomMove(World world) {
        List<Coordinates> availableMoves = world.getAvailableMoves(coordinates, this);
        if (!availableMoves.isEmpty()) {
            Random rand = new Random();
            Coordinates randomMove = availableMoves.get(rand.nextInt(availableMoves.size()));
            makeStep(world, randomMove);
        }
    }

    public void makeStep(World world, Coordinates nextStep) {
        Entity targetEntity = world.getEntity(nextStep);

        // Проверяем, есть ли сущность, с которой можно взаимодействовать
        if (targetEntity != null && canInteract(targetEntity)) {
            interactWithEntity(world, targetEntity, nextStep);
        }


        if (world.isCellPassable(nextStep, this)) {
            world.moveEntity(this.coordinates, nextStep, this);
            this.coordinates = nextStep;
        }
    }

    public void processInteraction(World world, Coordinates target) {
        Entity entity = world.getEntity(target);
        if (entity != null && canInteract(entity)) {
            System.out.println("Interacting with entity at " + target + ": " + entity);
            interactWithEntity(world, entity, target);
        }
    }

    protected abstract void interactWithEntity(World world, Entity entity, Coordinates target);

    public abstract void makeMove(World world, List<Coordinates> path);
}