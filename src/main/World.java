package main;

import main.Actions.MarkedEntitiesRemoverAction;
import main.Entities.*;
import main.exeptions.EntityNotFoundException;
import main.exeptions.NoFreeCoordinatesException;
import main.utils.Coordinates;

import java.util.*;

public class World {
    // x - это высота, а y - ширина
    private final int WIDTH;
    private final int HEIGHT;
    private final Map<Coordinates, Entity> entities = new HashMap<>();


    public World(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.values());
    }


    public void addEntity(Entity entity, Coordinates coordinates) {
        if(isCoordinateInMap(coordinates) && isCoordinateFree(coordinates)) {
            entities.put(coordinates, entity);
        }else {
            try {
                entities.put(coordinates, entity);
            } catch (IllegalArgumentException e) {
                System.out.println("Coordinates isn't empty or out of bounds");
            }
        }
    }

    public Coordinates getCoordinates(Entity entity) {

        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }
        throw new EntityNotFoundException("Entity not found in the world.");

    }


    public boolean containsEntity(Creature creature) {
        return entities.containsValue(creature);
    }

    public void removeEntity(Coordinates coordinates) {
        // удаление сущности
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        // получение сущности по координатам

        return entities.get(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to, Entity entity) {


        if (entities.get(from).equals(entity)) {
            entities.remove(from);
        }
        entities.put(to, entity);
    }





    public boolean isCellPassable(Coordinates coordinates, Creature creature) {
        // проверка на то что координата дотижима и в пределах карты
        if (!isCoordinateInMap(coordinates)) {
            return false;
        }


        Entity entity = entities.get(coordinates);
        if (entity == null) {
            return true;
        }
        if(isCoordinateFree(coordinates)) {
            return true;
        }

        if (creature instanceof Herbivore) {
            return !(entity instanceof Tree || entity instanceof Predator || entity instanceof Rock || entity instanceof Herbivore);
        } else if (creature instanceof Predator) {
            return !(entity instanceof Tree || entity instanceof Rock || entity instanceof Predator || entity instanceof Grass);
        }
        return false;
    }


    private boolean isCoordinateFree(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
    private boolean isCoordinateInMap(Coordinates coordinates) {

        return coordinates.lines >= 0 && coordinates.lines < HEIGHT && coordinates.columns >= 0 && coordinates.columns < WIDTH;
    }


    public Coordinates makePositionForNewEntity() {
        Random random = new Random();

        Coordinates randomCoordinateForEntity = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));

        try {
            int attempts = this.getWorldSquare();
            while (!isCoordinateFree(randomCoordinateForEntity) && attempts > 0) {
                randomCoordinateForEntity = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));
                attempts--;
            }
            return randomCoordinateForEntity;
        } catch (NoFreeCoordinatesException e) {
            throw new NoFreeCoordinatesException("No free coordinates found.");
        }

    }



    public List<Coordinates> getAvailableMoves(Coordinates coordinates, Creature creature) {

        List<Coordinates> moves = List.of(coordinates.shift(0, 1),
                coordinates.shift(0, -1), coordinates.shift(1, 0),
                coordinates.shift(-1, 0));

        List<Coordinates> validMoves = new ArrayList<>();

        for (Coordinates move : moves) {
            if (isCellPassable(move, creature)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {

        return WIDTH;
    }

    public int getWorldSquare() {
        return WIDTH * HEIGHT;
    }
}
