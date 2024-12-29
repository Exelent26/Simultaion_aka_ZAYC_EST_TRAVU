package utils;

import main.Entities.Entity;
import main.Entities.EntityNotFoundException;
import main.World;

import java.util.*;



public class ImprovedBfsPathFinder {

    private final World worldMap;

    public ImprovedBfsPathFinder(World worldMap) {
        this.worldMap = worldMap;
    }

    public <T extends Entity> List<Coordinates> findPath(Coordinates startCoordinates, Class<?> targetEntity) {
        // Очередь для utils.BFS
        Deque<Coordinates> queue = new ArrayDeque<>();
        // Карта для отслеживания пути
        Map<Coordinates, Coordinates> seenCoordinates = new HashMap<>();

        // Инициализация utils.BFS
        queue.add(startCoordinates);
        seenCoordinates.put(startCoordinates, null); // null - стартовая точка не имеет родителя

        // Поиск цели
        while (!queue.isEmpty()) {
            Coordinates currentCoordinates = queue.pollFirst();

            // Если ячейка не пуста, проверяем её содержимое
            if (!worldMap.isCoordinateFree(currentCoordinates)) {
                try {
                    Entity entity = worldMap.getEntity(currentCoordinates);
                    if (targetEntity.isInstance(entity)) {
                        return reconstructPath(currentCoordinates, seenCoordinates);
                    }
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Добавляем соседние координаты в очередь
            for (Coordinates neighbor : worldMap.getAvailableDirectionsForMove(currentCoordinates)) {
                if (!seenCoordinates.containsKey(neighbor)) {
                    queue.add(neighbor);
                    seenCoordinates.put(neighbor, currentCoordinates);
                }
            }
        }

        // Цель не найдена
        return Collections.emptyList();
    }

    // Восстановление пути от цели к началу
    private List<Coordinates> reconstructPath(Coordinates goalCoordinates, Map<Coordinates, Coordinates> seenCoordinates) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = goalCoordinates;
        while (current != null) {
            path.add(current);
            current = seenCoordinates.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    // Получение соседних координат
    /*private List<utils.Coordinates> getConnectedCoordinates(utils.Coordinates coordinates) {
        List<utils.Coordinates> neighbors = new ArrayList<>();
        int x = coordinates.getX();
        int y = coordinates.getY();

        // Добавляем соседей (вверх, вниз, влево, вправо)
        neighbors.add(new utils.Coordinates(x - 1, y));
        neighbors.add(new utils.Coordinates(x + 1, y));
        neighbors.add(new utils.Coordinates(x, y - 1));
        neighbors.add(new utils.Coordinates(x, y + 1));

        // Проверяем валидность координат
        neighbors.removeIf(neighbor -> !worldMap.areCoordinatesValid(neighbor));
        return neighbors;
    }*/
}
