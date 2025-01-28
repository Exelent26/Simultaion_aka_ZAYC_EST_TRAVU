package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected final Class<? extends Entity> foodType;
    public int health;
    public int speed;
    public boolean alive;
    public int hunger;
    public int maxHungerLvl;
    public int maxHealth;

    public Creature(Class<? extends Entity> foodType, int health, int speed,int maxHunger) {
        this.foodType = foodType;
        this.health = health;
        this.speed = speed;
        this.alive = true;
        this.hunger = 0;
        this.maxHungerLvl = maxHunger;
        this.alive = true;
        this.maxHealth = health;
    }

    public Class<? extends Entity> getFoodType() {
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

    public boolean isDead() {
        return this.health <= 0;

    }
    public void increaseHunger() {
        hunger++;
        if (hunger >= maxHungerLvl) {
            hunger = maxHungerLvl;
            health--; // Уменьшаем здоровье из-за сильного голода
            System.out.println(this + " is starving! Health: " + health);
            if (health <= 0) {
                alive = false;
            }
        }
    }
    public void eat(int hungerRestoration, int healthRestoration) {
        hunger = Math.max(hunger - hungerRestoration, 0); // Уменьшаем голод
        health = Math.min(health + healthRestoration, maxHealth); // Восстанавливаем здоровье
        System.out.println(this + " eats and restores hunger to " + hunger + " and health to " + health);
    }



    protected void markAsDead(World world) {
        if (isDead()) {
            Coordinates coordinates = world.getCoordinates(this);
            world.markForRemoval(coordinates);
            this.alive = false;
        }
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