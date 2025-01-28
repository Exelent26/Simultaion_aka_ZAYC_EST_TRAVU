package main;

import main.Entities.*;

import java.util.function.Supplier;

public enum EntitySpawnConfig {

    HERBIVORE(
            () -> new Herbivore(),
            0.03,
            1
    ),
    PREDATOR(
            () -> new Predator(),
            0.01,
            1
    ),
    GRASS(
            () -> new Grass(),
            0.1,
            1
    ),
    ROCK(
            () -> new Rock(),
            0.05,
            1
    ),
    TREE(
            () -> new Tree(),
            0.03,
            1
    )
    ;

    private final Supplier<Entity> supplier;
    private final double percentOfMap;
    private final int minimumOfEntityType;

    EntitySpawnConfig(Supplier<Entity> supplier, double percentOfMap, int minimumOfEntityType) {
        this.supplier = supplier;
        this.minimumOfEntityType = minimumOfEntityType;
        this.percentOfMap = percentOfMap;
    }

    public Supplier<Entity> getSupplier() {
        return supplier;
    }

    public double getPercentOfMap() {
        return percentOfMap;
    }

    public int getMinimumOfEntityType() {
        return minimumOfEntityType;
    }
}
