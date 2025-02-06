package main.Actions;

import main.Configs.EntitySpawnConfig;
import main.World;

public class InitialActions implements Action {



    public void execute(World world) {

        for(EntitySpawnConfig entitySpawnConfig : EntitySpawnConfig.values()) {
            SpawnAction spawnAction = new SpawnAction(entitySpawnConfig);
            spawnAction.execute(world);
        }
    }
}