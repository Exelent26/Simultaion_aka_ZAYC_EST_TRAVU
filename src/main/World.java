package main;

import main.Entities.*;
import main.utils.Coordinates;

import java.util.*;

public class World {
    public static final int WIDTH = SimulationConfig.WORLD_WIDTH;
    public static final int HEIGHT = SimulationConfig.WORLD_HEIGHT;
    private final Map<Coordinates, Entity> entities = new HashMap<>();


    public Map<Coordinates, Entity> getEntities() {
        // получение хэшмапы со всему сущностями в мире
        return entities;
    }


    public void addEntity(Entity entity) {
        entities.put(entity.getCoordinates(), entity);
        if (entity instanceof Predator || entity instanceof Herbivore) {
            System.out.println("Entity added: " + entity + " at " + entity.getCoordinates());
        }
    }

    public boolean isCellAvailable(Coordinates coordinates, Creature creature) {
        // координаты находятся в пределах мира
        if (!isCoordinateInMap(coordinates)) {
            return false;
        }

        // клетка проходима для конкретного энтити
        return isCellPassable(coordinates, creature);
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
        // тупое передвижение энтити из 1 координаты в другое, использую для премещения таргетов.
        if (entities.get(from).equals(entity)) {
            entities.remove(from);
        }
        entities.put(to, entity);
    }


    public boolean isCoordinateFree(Coordinates coordinates) {
        // проверка на то что координата свободна
        return !entities.containsKey(coordinates);
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

        if (creature instanceof Herbivore) {
            return !(entity instanceof Tree || entity instanceof Predator || entity instanceof Rock || entity instanceof Herbivore);
        } else if (creature instanceof Predator) {
            return !(entity instanceof Tree || entity instanceof Rock || entity instanceof Predator);
        }
        return false;
    }


    public Coordinates makeRandomPositionForEntity() {
        Random random = new Random();
        Coordinates randomCoordinateForFreeMove = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));//попробуем изменить wirld width на hight

        while (!isCoordinateFree(randomCoordinateForFreeMove)) {
            randomCoordinateForFreeMove = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));
        }
        return randomCoordinateForFreeMove;

    }

    public boolean isCoordinateInMap(Coordinates coordinates) {

        return coordinates.lines >= 0 && coordinates.lines < HEIGHT && coordinates.columns >= 0 && coordinates.columns < WIDTH;
    }

    public List<Creature> getAllCreatures() {
        return entities.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
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
            if (isCoordinateInMap(move) && isCellPassable(move, creature)) { // Используем isCellPassable
                validMoves.add(move);
            }
        }
        return validMoves;
    }
}
