package main;

import main.Entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityQuantityCalculator {
    public static int calculateQuantity(int worldSquare, double percent, int minQuantity){

        int quantity = (int) (worldSquare*percent/100);

        return Math.max(quantity, minQuantity);

    }
    public static double calculatePartOfEntityInMap(int worldSquare, World world, Class<? extends Entity> typeOfEntity){
        List<Entity> entities = world.getListOfEntities();

        int correctQuantityOfEntityInMap = (int)entities.stream().filter(typeOfEntity::isInstance).count();

          return (double) correctQuantityOfEntityInMap /worldSquare;
    }
}
