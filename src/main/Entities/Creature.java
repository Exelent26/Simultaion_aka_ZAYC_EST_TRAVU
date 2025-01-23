package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected final Class<?> foodType;
    public int health;
    public int speed;

    public Creature(Class<?> foodType, int health, int speed) {
        this.foodType = foodType;
        this.health = health;
        this.speed = speed;
    }

    public Class<?> getFoodType() {
        return foodType;
    }

    public boolean canInteract(Entity entity) {
        return foodType.isInstance(entity);
    }

    public void makeRandomMove(World world) {
        Coordinates currentCoordinates = world.getCoordinates(this);
        List<Coordinates> availableMoves = world.getAvailableMoves(currentCoordinates, this);
        if (!availableMoves.isEmpty()) {
            Coordinates randomMove = availableMoves.get(new Random().nextInt(availableMoves.size()));
            makeStep(world, randomMove);
            System.out.println("Random moving entity " + this + " from " + currentCoordinates + " to " + randomMove);
        }
    }
    public boolean isDead(Creature creature) {
        return creature.health <= 0;

    }

    public void makeStep(World world, Coordinates nextStep) {
        Entity targetEntity = world.getEntity(nextStep);

        if (targetEntity != null && canInteract(targetEntity)) {
            interactWithEntity(world, targetEntity, nextStep);
        }

        Coordinates currentCoordinates = world.getCoordinates(this);
        if (world.isCellPassable(nextStep, this)) {
            world.moveEntity(currentCoordinates, nextStep, this);
        }
    }

    /*public void processInteraction(World world, Coordinates target) {
        Entity entity = world.getEntity(target);
        if (entity != null && canInteract(entity)) {
            System.out.println("Interacting with entity at " + target + ": " + entity);
            interactWithEntity(world, entity, target);
        }
    }*/

    protected abstract void interactWithEntity(World world, Entity entity, Coordinates target);

    public abstract void makeMove(World world, Coordinates nextStep);

    public int getSpeed() {
        return speed;
    }
}