package main.utils;

import main.Entities.Creature;
import main.Entities.Entity;
import main.World;
import main.exeptions.EntityNotFoundException;

import java.util.*;

public class BFS {

    public List<Coordinates> getPath(World world, Coordinates start, Class<? extends Entity> food) {
        Deque<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            Creature finder = (Creature) world.getEntity(start).orElseThrow(() -> new EntityNotFoundException("Creature not found"));
            for (Coordinates neighbor : world.getAvailableMoves(current, finder)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);

                    Entity entityAtNeighbor = world.getEntity(neighbor).orElse(null);
                    if (food.isInstance(entityAtNeighbor)) {
                        return reconstructPath(start, neighbor, cameFrom);
                    }
                }
            }
        }

        return new ArrayList<>();
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
