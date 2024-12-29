package main;

import main.Entities.*;
import utils.Coordinates;

import java.util.*;

public class World {
    public static final int WIDTH = SimulationConfig.WORLD_WIDTH;
    public static final int HEIGHT = SimulationConfig.WORLD_HEIGHT;
    private final Set<Coordinates> reservedCoordinates = new HashSet<>();

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public void addEntity(Entity entity) {
        entities.put(entity.getCoordinates(), entity);
        if(entity instanceof Predator || entity instanceof Herbivore) {
            System.out.println("Entity added: " + entity + " at " + entity.getCoordinates());
        }
    }
    public boolean isCellAvailable(Coordinates coordinates, Creature creature) {
        // координаты находятся в пределах мира
        if (!isWithinBounds(coordinates)) {
            return false;
        }

        // клетка проходима для конкретного энтити
        return isCellPassable(coordinates, creature);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to, Entity entity) {
        if(entities.get(from).equals(entity)) {
            entities.remove(from);
        }
        entities.put(to, entity);
    }


    public boolean isCoordinateFree(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
    public boolean isCellPassable(Coordinates coordinates, Creature creature) {
        if (!isWithinBounds(coordinates)) {
            return false;
        }

        Entity entity = entities.get(coordinates);
        if (entity == null) {
            return true;
        }

        if (creature instanceof Herbivore) {
            return !(entity instanceof Tree || entity instanceof Predator || entity instanceof Rock || entity instanceof Herbivore);
        } else if (creature instanceof Predator) {
            return !(entity instanceof Tree || entity instanceof Rock || entity instanceof Predator);
        }
        return false;
    }


    public boolean isWithinBounds(Coordinates coordinates) {
        return coordinates.x >= 0 && coordinates.x < HEIGHT && coordinates.y >= 0 && coordinates.y < WIDTH;
    }

    public Coordinates makeRandomPositionForEntity() {
        Random random = new Random();
        Coordinates randomCoordinateForFreeMove = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));//попробуем изменить wirld width на hight

        while(!isCoordinateFree(randomCoordinateForFreeMove)) {
            randomCoordinateForFreeMove = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));
        }
        return randomCoordinateForFreeMove;

    }

    public List<Coordinates> getAvailableMoves(Coordinates coordinates, Creature creature) {
        List<Coordinates> moves = Arrays.asList(
                coordinates.shift(0, 1),
                coordinates.shift(0, -1),
                coordinates.shift(1, 0),
                coordinates.shift(-1, 0)
        );

        List<Coordinates> validMoves = new ArrayList<>();
        for (Coordinates move : moves) {
            if (isWithinBounds(move) && isCellPassable(move, creature)) { // Используем isCellPassable
                validMoves.add(move);
            }
        }
        return validMoves;
    }
}
