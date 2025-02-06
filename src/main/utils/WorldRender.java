package main.utils;

import main.Entities.*;
import main.World;

public class WorldRender {

    public static final String PREDATOR_SPRITE = "\uD83E\uDD81";
    public static final String HERBIVORE_SPRITE = "\uD83D\uDC07";
    public static final String TREE_SPRITE = "\uD83C\uDF84";
    public static final String GRASS_SPRITE = "\uD83C\uDF31";
    public static final String ROCK_SPRITE = "\uD83E\uDEA8";
    public static final String FREE_SQUARE = "\uD83D\uDFEB";

    public void render(World world) {


        for (int x = 0; x < world.getHight(); x++) {
            System.out.println();
            for (int y = 0; y < world.getWidth(); y++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity tempCoordinates = world.getEntity(coordinates);
                switch (tempCoordinates) {
                    case Herbivore herbivore -> System.out.print(HERBIVORE_SPRITE);
                    case Grass grass -> System.out.print(GRASS_SPRITE);
                    case Tree tree -> System.out.print(TREE_SPRITE);
                    case Rock rock -> System.out.print(ROCK_SPRITE);
                    case Predator predator -> System.out.print(PREDATOR_SPRITE);
                    case null -> System.out.print(FREE_SQUARE);
                    default -> throw new IllegalStateException("Unexpected value: " + tempCoordinates);
                }

            }

        }
        System.out.println();
    }

}
