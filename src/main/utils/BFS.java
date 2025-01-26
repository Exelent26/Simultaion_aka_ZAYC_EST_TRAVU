package main.utils;

import main.Entities.Creature;
import main.Entities.Entity;
import main.World;

import java.nio.file.Path;
import java.util.*;

public class BFS {

    /*private Map<Coordinates, Coordinates> bfs(Creature finder, World world) {
        Coordinates start = world.getCoordinates(finder);
        Class<?> targetClass = finder.getFoodType();

        Deque<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            for (Coordinates neighbor : world.getAvailableMoves(current, finder)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);

                    Entity entityAtNeighbor = world.getEntity(neighbor);
                    if (entityAtNeighbor != null && targetClass.isInstance(entityAtNeighbor)) { // Используем isInstance
                        return cameFrom;
                    }
                }
            }
        }
        return new HashMap<>();
    }

    private List<Coordinates> pathfinder(Creature baseEntity, World world) {
        Map<Coordinates, Coordinates> cameFrom = bfs(baseEntity, world);

        if (cameFrom.isEmpty()) {
            return new ArrayList<>();
        }

        Coordinates targetCoordinates = null;

        for (Coordinates coordinate : cameFrom.keySet()) {
            Entity entity = world.getEntity(coordinate);
            if (entity != null && baseEntity.getFoodType().isInstance(entity)) {
                targetCoordinates = coordinate;
                break;
            }
        }

        if (targetCoordinates == null) {
            return new ArrayList<>();
        }

        List<Coordinates> path = new ArrayList<>();
        Coordinates current = targetCoordinates;

        while (current != null && !current.equals(world.getCoordinates(baseEntity))) {
            path.add(current);
            current = cameFrom.get(current);
        }

        path.add(world.getCoordinates(baseEntity));
        Collections.reverse(path);

        return path;
    }

    public Coordinates nextStepFromPath(Creature baseEntity, World world) {

        List<Coordinates> path = pathfinder(baseEntity, world);

        return !path.isEmpty() ? path.get(1) : null;
    }*/
    public List<Coordinates> getPath(World world, Coordinates start, Class<? extends Entity> food) {
        Deque<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        queue.add(start);
        visited.add(start);

        // BFS-алгоритм
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            for (Coordinates neighbor : world.getAvailableMoves(current, null)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);

                    Entity entityAtNeighbor = world.getEntity(neighbor);
                    if (entityAtNeighbor != null && food.isInstance(entityAtNeighbor)) {
                        return reconstructPath(start, neighbor, cameFrom);
                    }
                }
            }
        }

        return new ArrayList<>(); // Путь не найден
    }

    private List<Coordinates> reconstructPath(Coordinates start, Coordinates end, Map<Coordinates, Coordinates> cameFrom) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = end;

        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
