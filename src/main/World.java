package main;

import main.Entities.*;
import main.exeptions.EntityNotFoundException;
import main.exeptions.NoFreeCoordinatesException;
import main.utils.Coordinates;

import java.util.*;

public class World {
    private final int WIDTH;
    private final int HEIGHT;
    private final Map<Coordinates, Entity> entities = new HashMap<>();


    public World(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("World size must be positive");
        }
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


    public void removeEntity(Coordinates coordinates) {
        try {
            entities.remove(coordinates);
        }catch (IllegalArgumentException e) {
            System.out.println("Coordinates not inside the world");
        }

    }

    public Optional<Entity> getEntity(Coordinates coordinates) {

        return Optional.ofNullable(entities.get(coordinates));

    }

    public void moveEntity(Coordinates from, Coordinates to, Entity entity) {

        if (entities.get(from).equals(entity)) {
            entities.remove(from);
        }
        entities.put(to, entity);
    }

    public boolean isCellPassable(Coordinates coordinates, Creature creature) {
        Objects.requireNonNull(coordinates, "Coordinates cannot be null");
        Objects.requireNonNull(creature, "Creature cannot be null");

        if (!isCoordinateInMap(coordinates)) return false;
        if (isCoordinateFree(coordinates)) return true;

        Entity entity = entities.get(coordinates);
        return checkPassability(creature, entity);
    }

    private boolean checkPassability(Creature creature, Entity entity) {
        if (creature instanceof Herbivore) {
            return !(entity instanceof Tree || entity instanceof Predator || entity instanceof Rock || entity instanceof Herbivore);
        }
        if (creature instanceof Predator) {
            return !(entity instanceof Tree || entity instanceof Rock || entity instanceof Predator || entity instanceof Grass);
        }
        return false;
    }


    private boolean isCoordinateFree(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
    private boolean isCoordinateInMap(Coordinates coordinates) {

        return coordinates.getLines() >= 0 && coordinates.getLines() < HEIGHT && coordinates.getColumns() >= 0 && coordinates.getColumns() < WIDTH;
    }


    public Coordinates makePositionForNewEntity() {
        List<Coordinates> freeCoordinates = new ArrayList<>();
        Random random = new Random();

        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Coordinates coord = new Coordinates(x, y);
                if (isCoordinateFree(coord)) {
                    freeCoordinates.add(coord);
                }
            }
        }

        if (!freeCoordinates.isEmpty()) {
            return freeCoordinates.get(random.nextInt(freeCoordinates.size()));
        }

        throw new NoFreeCoordinatesException("No free coordinates available in the world");
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
