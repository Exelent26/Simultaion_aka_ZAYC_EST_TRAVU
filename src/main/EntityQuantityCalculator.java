package main;

public class EntityQuantityCalculator {
    public static int calculateQuantity(int worldSquare, double percent, int minQuantity){

        int quantity = (int) (worldSquare*percent);

        return Math.max(quantity, minQuantity);

    }
}
