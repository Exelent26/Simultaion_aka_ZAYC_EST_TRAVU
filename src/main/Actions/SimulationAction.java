package main.Actions;

import main.Configs.CreatureAndGrassRespawnConfig;
import main.Entities.Creature;
import main.World;
import main.utils.BFS;
import main.utils.Coordinates;

import java.util.List;
import java.util.stream.Collectors;

public class SimulationAction implements Action {

    @Override
    public void execute(World world) {

        for(CreatureAndGrassRespawnConfig config: CreatureAndGrassRespawnConfig.values()) {
            ReSpawnAction respawnAction = new ReSpawnAction(config);
            respawnAction.execute(world);
        }

        List<Creature> creatures = (world.getEntities()).stream().
                filter(entity -> entity instanceof Creature).
                map(entity -> (Creature) entity).toList();;
        BFS bfs = new BFS();

        for (Creature creature : creatures) {
            if (world.containsEntity(creature) && creature.alive) {
                creature.increaseHunger();
                if (!creature.isDead()) {
                    for (int i = 0; i < creature.getSpeed(); i++) {
                        List<Coordinates> path = bfs.getPath(world, world.getCoordinates(creature), creature.getFoodType());
                        if (!path.isEmpty()) {
                            Coordinates nextStep = path.get(1);
                            if (nextStep != null && world.isCellPassable(nextStep, creature)) {
                                creature.makeMove(world, nextStep);
                            }
                        }else {
                            creature.makeRandomMove(world);
                        }
                    }
                }
            }
        }

    }

}