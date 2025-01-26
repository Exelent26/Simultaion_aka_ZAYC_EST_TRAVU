package main.Entities;

import main.World;
import main.utils.Coordinates;

import java.util.List;

public class Herbivore extends Creature {

    private int prepareToEatCounter = 0;

    public Herbivore() {
        super(Grass.class, 25, 1,15);
    }

    @Override
    /*public void makeMove(World world, Coordinates nextStep) {
        if (nextStep == null) {
            makeRandomMove(world);

        }else {
            Coordinates currentCoordinates = world.getCoordinates(this);
            System.out.println("Moving entity " + this + " from " + currentCoordinates + " to " + nextStep);
            makeStep(world, nextStep);
        }

        //processInteraction(world, nextStep); возможно лишний метод дублирование makeStep
    }*/
    public void makeMove(World world, Coordinates nextStep) {
        if(!alive){
            return;
        }
        if (nextStep == null) {
            makeRandomMove(world);
            return;
        }
        Entity entity = world.getEntity(nextStep);
        if (entity instanceof Grass) {
            interactWithEntity(world, entity, nextStep);
        }else {
            Coordinates currentCoordinates = world.getCoordinates(this);
            System.out.println("Moving entity " + this + " from " + currentCoordinates + " to " + nextStep);
            makeStep(world, nextStep);
        }
    }


    /*@Override*/
    /*protected void interactWithEntity(World world, Entity entity, Coordinates target) {
        if (entity != null) {
            List<Coordinates> adjacentCoordinates = world.getAvailableMoves(target, this);
            for (Coordinates adjacentCoordinate : adjacentCoordinates) {
                Entity adjacentEntity = world.getEntity(adjacentCoordinate);
                if (adjacentEntity instanceof Grass) {
                    if (prepareToEatCounter == 0) {
                        prepareToEatCounter++;
                        System.out.println(this+ " gotov kushat' travu");
                    } else if (prepareToEatCounter == 1) {
                        world.removeEntity(adjacentCoordinate);
                        System.out.println(this + " skushal travu");
                        prepareToEatCounter = 0;
                        makeStep(world, adjacentCoordinate);
                    }
                }
            }

        }
    }*/
    /*protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        Entity target = world.getEntity(targetCoordinates);
        if(target instanceof Grass && prepareToEatCounter == 0) {
            prepareToEatCounter++;
            System.out.println(this+ " gotov kushat' travu");
        }
        else if(target instanceof Grass && prepareToEatCounter == 1){
            world.removeEntity(targetCoordinates);
            System.out.println("Herbivore " + this + " eat  "+ target + targetCoordinates);
        }
    }*/
    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        if (entity instanceof Grass grass) {
            if (!grass.isEaten() && prepareToEatCounter == 1) {
                // Если трава ещё не съедена, помечаем её как съеденную
                grass.markAsEaten();
                world.markForRemoval(targetCoordinates); // Помечаем для удаления
                eat(25, 15); // Восстанавливаем голод и здоровье
                prepareToEatCounter = 0; // Сбрасываем счётчик подготовки
                System.out.println(this + " ate grass at " + targetCoordinates);
            } else if (!grass.isEaten() && prepareToEatCounter == 0) {
                // Если только начали готовиться к поеданию
                prepareToEatCounter++;
                System.out.println(this + " is preparing to eat grass.");
            } else {
                // Если трава уже съедена другим существом
                prepareToEatCounter = 0;
                System.out.println(this + " couldn't eat grass at " + targetCoordinates + " because it's already eaten.");
            }
        }
    }
}