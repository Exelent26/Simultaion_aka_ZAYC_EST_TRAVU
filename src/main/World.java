package main;

import main.Entities.*;
import main.exeptions.CoordinateNotValidException;
import main.exeptions.EntityNotFoundException;
import main.utils.Coordinates;

import java.util.*;

public class World {
    public static final int WIDTH = SimulationConfig.WORLD_WIDTH;
    public static final int HEIGHT = SimulationConfig.WORLD_HEIGHT;
    private final Map<Coordinates, Entity> entities = new HashMap<>();
    private final List<Coordinates> entitiesToRemove = new ArrayList<>();



    public Map<Coordinates, Entity> getEntities() {
        // получение хэшмапы со всему сущностями в мире
        return entities;
    }


    public void addEntity(Entity entity, Coordinates coordinates) {
        entities.put(coordinates, entity);
        if (entity instanceof Predator || entity instanceof Herbivore) {
            System.out.println("Entity added: " + entity + " at " + coordinates);
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

    public void markForRemoval(Coordinates coordinates) {
        if (!entitiesToRemove.contains(coordinates)) {
            entitiesToRemove.add(coordinates);
        }
    }

    public void cleanupDeadEntities() {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            Coordinates coordinates = entry.getKey();
            Entity entity = entry.getValue();

            if (entity instanceof Creature creature && creature.isDead()) {
                markForRemoval(coordinates);
            }
            else if (entity instanceof Grass grass && grass.isEaten()) {
                markForRemoval(coordinates);
            }
        }
        for (Coordinates coordinates : entitiesToRemove) {
            Entity removed = entities.remove(coordinates);
            System.out.println("Removed entity: " + removed + " from " + coordinates);
        }

        entitiesToRemove.clear();
    }

    public boolean isCellAvailable(Coordinates coordinates, Creature creature) {
        // координаты находятся в пределах мира
        if (!isCoordinateInMap(coordinates)) {
            return false;
        }

        if (entitiesToRemove.contains(coordinates)) {
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
        if (entitiesToRemove.contains(to)) {
            System.out.println("Cannot move entity " + entity + " to " + to + ". Cell is marked for removal.");
            return;
        }
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
            return !(entity instanceof Tree || entity instanceof Rock || entity instanceof Predator || entity instanceof Grass);
        }
        return false;
    }


    public Coordinates makeRandomPositionForEntity() {
        Random random = new Random();
        Coordinates randomCoordinateForEntity = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));//попробуем изменить wirld width на hight

        while (!isCoordinateFree(randomCoordinateForEntity) && !isCoordinateInMap(randomCoordinateForEntity)) {
            randomCoordinateForEntity = new Coordinates(random.nextInt(HEIGHT), random.nextInt(WIDTH));
        }

            return randomCoordinateForEntity;


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

    public int getHight() {
        return SimulationConfig.WORLD_HEIGHT;
    }

    public int getWidth() {

    return SimulationConfig.WORLD_WIDTH;}
}
