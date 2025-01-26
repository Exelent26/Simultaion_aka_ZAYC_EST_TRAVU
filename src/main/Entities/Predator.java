package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower = 10;

    public Predator(Coordinates coordinates) {
        super(Herbivore.class, 25, 2,25);
    }

    @Override
    public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep);
        if (entity instanceof Herbivore) {
            interactWithEntity(world, entity, nextStep);
        } else {
            Coordinates currentCoordinates = world.getCoordinates(this);
            System.out.println("Moving entity " + this + " from " + currentCoordinates + " to " + nextStep);
            makeStep(world, nextStep);
        }
    }


    private void attack(World world, Coordinates nextStep) {
        Entity target = world.getEntity(nextStep);
        if (target instanceof Herbivore) {
            ((Herbivore) target).health -= 20;
            System.out.println("Predator " + this + " attacks Herbivore at " + nextStep);

        }
    }

    /*@Override*/
    /*protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        Entity target = world.getEntity(targetCoordinates);
        if(target instanceof Herbivore &&((Herbivore) target).health>0) {
            this.attack(world, targetCoordinates);
        }
        else if(target instanceof Herbivore &&((Herbivore) target).health<=0){
            if(((Herbivore) target).isDead()){
                ((Herbivore) target).alive = false;
            }
            world.removeEntity(targetCoordinates);
            System.out.println("Predator " + this + " kill "+ target + targetCoordinates);
        }
    }*/
    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        if (entity instanceof Herbivore herbivore) {
            if (herbivore.health > 0) {
                // Если у травоядного ещё есть здоровье, атакуем
                herbivore.health -= attackPower;
                System.out.println("Predator " + this + " attacks Herbivore at " + targetCoordinates);

                // Если травоядное стало мёртвым после атаки
                if (herbivore.isDead()) {
                    System.out.println("Herbivore at " + targetCoordinates + " is now dead. Killed by " + this);
                    world.markForRemoval(targetCoordinates); // Помечаем травоядное для удаления
                    eat(20, 15); // Восстанавливаем голод и здоровье
                }
            } else {
                // Если травоядное уже мертво, не атакуем
                return;
            }
        }
    }
}
