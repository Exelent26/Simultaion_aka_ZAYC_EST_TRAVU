package main.Configs;

import main.Entities.*;

import java.util.function.Supplier;

public enum EntitySpawnConfig {

    HERBIVORE(
            Herbivore::new,
            4,
            3
    ),
    PREDATOR(
            Predator::new,
            1,
            2
    ),
    GRASS(
            Grass::new,
            12,
            1
    ),
    ROCK(
            Rock::new,
            7,
            1
    ),
    TREE(
            Tree::new,
            5,
            1
    )
    ;

    private final Supplier<Entity> supplier;
    private final int percentOfMap;
    private final int minimumOfEntityType;


    EntitySpawnConfig(Supplier<Entity> supplier, int percentOfMap, int minimumOfEntityType) {
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
