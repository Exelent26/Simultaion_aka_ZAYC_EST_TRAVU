package main.Entities;

import main.World;
import main.utils.Coordinates;

public class Herbivore extends Creature {

    private int prepareToEatCounter = 0;

    public Herbivore() {
        super(Grass.class, 25, 1,5);
    }

    @Override
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



    @Override
    protected void interactWithEntity(World world, Entity entity, Coordinates targetCoordinates) {
        if (entity instanceof Grass grass) {
            if (!grass.isEaten() && prepareToEatCounter == 1) {
                // Если трава ещё не съедена, помечаем её как съеденную
                grass.markAsEaten();
                world.markForRemoval(targetCoordinates); // Помечаем для удаления
                eatTarget(25, 15); // Восстанавливаем голод и здоровье
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