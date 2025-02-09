package main.utils;

import main.Entities.Entity;
import main.World;

import java.util.List;

public class EntityQuantityCalculator {
    public static int calculateQuantity(int worldSquare, double percent, int minQuantity){

        int quantity = (int) (worldSquare*percent/100);

        return Math.max(quantity, minQuantity);

    }
    public static double calculatePartOfEntityInMap(World world, Class<? extends Entity> typeOfEntity){
        List<Entity> entities = world.getEntities();

        int correctQuantityOfEntityInMap = (int)entities.stream().
                filter(typeOfEntity::isInstance).count();
            double worldSquare = world.getWorldSquare();

          return (correctQuantityOfEntityInMap /worldSquare)*100 ;
    }
}
