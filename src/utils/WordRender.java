package main;

import main.Entities.*;

public class WordRender {

    public static final String PREDATOR_SPRITE = "\uD83E\uDD81";
    public static final String HERBIVORE_SPRITE = "\uD83D\uDC07";
    public static final String TREE_SPRITE = "\uD83C\uDF84";
    public static final String GRASS_SPRITE = "\uD83C\uDF31";
    public static final String ROCK_SPRITE = "\uD83E\uDEA8";
    public static final String FREE_SQUARE = "\uD83D\uDFEB";

    public static void worldRender(World world) {

        for (int x = 0; x < World.WORLD_HEIGHT; x++) {
            System.out.println();
            for (int y = 0; y < World.WORLD_WIDTH; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity tempCoordinates = World.entities.get(coordinates);
                if(tempCoordinates instanceof Herbivore){
                    System.out.print(HERBIVORE_SPRITE);
                } else if(tempCoordinates instanceof Grass){
                    System.out.print(GRASS_SPRITE);
                }else if(tempCoordinates instanceof Rock) {
                    System.out.print(ROCK_SPRITE);
                }else if(tempCoordinates instanceof Predator) {
                    System.out.print(PREDATOR_SPRITE);
                }else {
                    System.out.print(FREE_SQUARE);
                }
            }

        }
    }

}
