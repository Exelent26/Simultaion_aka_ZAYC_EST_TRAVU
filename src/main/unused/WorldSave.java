/*
package main;

import main.Entities.*;
import main.utils.Coordinates;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    public static final int WORLD_WIDTH = 10;
    public static final int WORLD_HEIGHT = 10;
    static int HERBIVORE_QUANTITY = 4;
    public static int GRASS_QUANTITY = 15;
    static int ROCK_QUANTITY = 1;
    static int PREDATOR_QUANTITY = 2;


    static Random random = new Random();
    public static List<Creature> creatures = new ArrayList<>();
    public static List<Herbivore> herbivores = new ArrayList<>();
    public static List<Creature> predator = new ArrayList<>();


    public static HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }
    public void createEntity(Entity entity) {
        entities.put(entity.coordinates, entity);

    }
    protected void worldCreation() { //TODO: перенести в экшн, тут не место

        int totalEntities = HERBIVORE_QUANTITY + GRASS_QUANTITY + ROCK_QUANTITY+PREDATOR_QUANTITY;
        int currentEntities = 0;

        while (currentEntities < totalEntities) {

            Coordinates tempCoordinates = makeRandomPositionForEntity();
            if (isCoordinateFree(tempCoordinates)) {
                Entity entity = null;
                if (currentEntities < HERBIVORE_QUANTITY) {
                    entity = new Herbivore(tempCoordinates);
                    creatures.add((Creature)entity);
                    herbivores.add((Herbivore)entity);
                } else if (currentEntities < HERBIVORE_QUANTITY+ROCK_QUANTITY) {
                    entity = new Rock(tempCoordinates);
                } else if (currentEntities < HERBIVORE_QUANTITY+ROCK_QUANTITY+PREDATOR_QUANTITY) {
                    entity = new Predator(tempCoordinates);
                    predator.add((Creature) entity);
                } else if(currentEntities < HERBIVORE_QUANTITY+ROCK_QUANTITY+PREDATOR_QUANTITY+GRASS_QUANTITY) {
                    entity = new Grass(tempCoordinates);
                }
                setEntity(tempCoordinates, entity);
                currentEntities++;
            }
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public List<Entity> getEntitiesOfType(Class<?> entityType) {
        return entities.values().stream()
                .filter(entity -> entity.getClass() == entityType)
                .collect(Collectors.toList());
    }

    public void moveEntity(Coordinates oldCoordinates, Coordinates newCoordinates, Entity entity) {
        entities.remove(oldCoordinates);
        setEntity(newCoordinates, entity);
    }
    public Coordinates makeRandomPositionForEntity() {
        Coordinates randomCoordinateForFreeMove = new Coordinates(random.nextInt(WORLD_WIDTH), random.nextInt(WORLD_HEIGHT));

        while(!isCoordinateFree(randomCoordinateForFreeMove)) {
            randomCoordinateForFreeMove = new Coordinates(random.nextInt(WORLD_WIDTH), random.nextInt(WORLD_HEIGHT));
        }
        return randomCoordinateForFreeMove;

    }

    public boolean isCoordinateFree(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public boolean isPlaceAvailableForMove(Coordinates coordinates) {// TODO: при переносе метода в симуляцию добавить хищника(Predator) и деревья(Threes)
        Entity entityInNewPosition = entities.get(coordinates);
        if (entityInNewPosition instanceof Rock ||  entityInNewPosition instanceof Predator) {
            return false;
        }

        return true;
    }

    public Coordinates makeCoordinatesForRandomMovement(Coordinates oldcoordinates, World world) {
        List<Coordinates> availableDirections = (world.getAvailableDirectionsForMove(oldcoordinates)).stream().toList();
        int randomDirection = (int) (Math.random() * availableDirections.size());
        return availableDirections.get(randomDirection);

    }

    public boolean isPositionInMap(Coordinates coordinates) {
        if (coordinates.POSITION_OF_WIDE < 0 || coordinates.POSITION_OF_WIDE >= WORLD_WIDTH) {
            return false;
        }
        if (coordinates.POSITION_OF_HEIGHT < 0 || coordinates.POSITION_OF_HEIGHT >= WORLD_HEIGHT) {
            return false;
        }
        return true;
    }

    public Set<Coordinates> getAvailablePositionsForMove(Coordinates coordinates) {
        return new HashSet<>(Arrays.asList(
                new Coordinates(coordinates.POSITION_OF_HEIGHT, coordinates.POSITION_OF_WIDE + 1),
                new Coordinates(coordinates.POSITION_OF_HEIGHT, coordinates.POSITION_OF_WIDE - 1),
                new Coordinates(coordinates.POSITION_OF_HEIGHT + 1, coordinates.POSITION_OF_WIDE),
                new Coordinates(coordinates.POSITION_OF_HEIGHT - 1, coordinates.POSITION_OF_WIDE)

        ));
    }

    public Set<Coordinates> getAvailableDirectionsForMove(Coordinates coordinates) {
        Set<Coordinates> availableDirections = new HashSet<>();
        for (Coordinates direction : getAvailablePositionsForMove(coordinates)) {
            if (isPositionInMap(direction) && isPlaceAvailableForMove(direction)) {
                availableDirections.add(direction);
            }
        }
        return availableDirections;
    }


    public void addCreatureToCreatureList(Creature entity) {
        creatures.add(entity);
    }


}*/
