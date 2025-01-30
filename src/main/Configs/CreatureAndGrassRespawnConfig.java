package main.Configs;

import main.Entities.*;

import java.util.function.Supplier;
 //TODO: сделать что может не заспавнится нужная энтити,
 // чтоб показать работу метода хождения в рандомную точку при отсутствии еды!
public enum CreatureAndGrassRespawnConfig {

    HERBIVORE(
            () -> new Herbivore(),
            Herbivore.class,
            0.7,
            0,
            2
    ),
    PREDATOR(
            () -> new Predator(),
            Predator.class,
            0.1,
            0,
            1),
    GRASS(
            () -> new Grass(),
            Grass.class,
            1,
            1,
            5);

    private final Supplier<Entity> supplier;
    private final Class<? extends Entity> entityClass;
    private final double minPercentOfMapForRespawn;
    private final int minimumEntityNumberToRespawn;
    private final int maximumEntityNumberToRespawn;

    CreatureAndGrassRespawnConfig(Supplier<Entity> supplier, Class<? extends Entity> entityClass, double percentOfMapForRespawn, int minimumEntityNumberToRespawn, int maximumEntityNumberToRespawn) {
        this.supplier = supplier;
        this.entityClass = entityClass;
        this.minPercentOfMapForRespawn = percentOfMapForRespawn;
        this.minimumEntityNumberToRespawn = minimumEntityNumberToRespawn;
        this.maximumEntityNumberToRespawn = maximumEntityNumberToRespawn;
    }

    public Supplier<Entity> getSupplier() {
        return supplier;
    }

    public double getMinPercentOfMapForRespawn() {
        return minPercentOfMapForRespawn;
    }

    public int getMinimumEntityNumberToRespawn() {
        return minimumEntityNumberToRespawn;
    }

    public int getMaximumEntityNumberToRespawn() {
        return maximumEntityNumberToRespawn;
    }

     public Class<? extends Entity> getEntityClass() {
         return entityClass;
     }
 }