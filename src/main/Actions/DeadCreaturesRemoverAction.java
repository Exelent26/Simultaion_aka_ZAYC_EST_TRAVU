package main.Actions;


import main.World;

public class DeadCreaturesRemoverAction implements Action {


    @Override
    public void execute(World world) {
        world.cleanupDeadEntities();


    }
}
