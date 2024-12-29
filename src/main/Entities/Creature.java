package main;

public abstract class Creature extends Entity {
    protected final Class<?> food;

    public Creature(Class<?> food, Coordinates coordinates) {
        super(coordinates); // Передача координат в базовый класс
        this.food = food;
    }

    public boolean canEat(Entity entity) {
        return food.isInstance(entity);
    }

    public Class<?> getFood() {
        return food;
    }

}