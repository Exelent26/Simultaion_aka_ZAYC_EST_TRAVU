package main;

import main.Entities.Creature;
import main.Entities.Entity;

import java.util.*;

public class BFS {


    private Map<Coordinates, Coordinates> bfs(Creature finder, World world)  {

        Coordinates start = finder.coordinates;

        Class<?> targetClass = finder.getFood();
        Deque<Coordinates> queue = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();
        Map<Coordinates, Coordinates> pathForReleasePath = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            for (Coordinates newCoordinate : world.getAvailableDirectionsForMove(current)) {
                if (!visited.contains(newCoordinate)) {
                    visited.add(newCoordinate);
                    queue.add(newCoordinate);
                    pathForReleasePath.put(newCoordinate, current);
                }

                Entity entityAtCoordinate = world.getEntity(newCoordinate);
                if (entityAtCoordinate != null && entityAtCoordinate.getClass() == targetClass) {
                    return pathForReleasePath;
                }
            }
        }
        //throw new PathNotFoundExeption("Path from " + start + " to target entity was not found.");
        return new HashMap<>();
    }
    public List<Coordinates> pathfinder(Creature baseEntity, World world) {
        Map<Coordinates, Coordinates> pathForReleasePath = bfs(baseEntity, world);

        if (pathForReleasePath.isEmpty()) {
            return new ArrayList<>();
        }
        Coordinates targetCoordinates = null;
        for (Coordinates coordinate : pathForReleasePath.keySet()) {
            Entity entity = world.getEntity(coordinate);
            if (entity != null && entity.getClass() == baseEntity.getFood()) {
                targetCoordinates = coordinate;
                break;
            }
        }
        if (targetCoordinates == null) {
            return new ArrayList<>();
        }
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = targetCoordinates;
        while (current != null && !current.equals(baseEntity.coordinates)) {
            path.add(current);
            current = pathForReleasePath.get(current);
        }
        path.add(baseEntity.coordinates);
        Collections.reverse(path);

        return path;
    }

}