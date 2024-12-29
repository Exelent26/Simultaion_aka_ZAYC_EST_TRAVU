/*


import main.World;
import utils.Coordinates;

import java.util.List;

public abstract class Creature extends Entity {
    protected final Class<?> foodType;
    protected int health;

    public Creature(Class<?> foodType, Coordinates coordinates, int health) {
        super(coordinates);
        this.foodType = foodType;
        this.health = health;
    }
    public Class<?> getFoodType() {
        return foodType;
    }

    public boolean canInteract(Entity entity) {
        return foodType.isInstance(entity);
    }

    public void makeStep(World world, Coordinates nextStep) {
        world.moveEntity(this.coordinates, nextStep, this);
        this.coordinates = nextStep;
    }

    public void processInteraction(World world, Coordinates target) {
        Entity entity = world.getEntity(target);
        if (entity != null && canInteract(entity)) {
            interactWithEntity(world, entity, target);
        }
    }

    protected abstract void interactWithEntity(World world, Entity entity, Coordinates target);

    public abstract void makeMove(World world, List<Coordinates> path);
}*/
