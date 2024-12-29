/*
        while (targetCount != 0) {
            for (int i = 0; i < main.World.creatures.size(); i++) {
                Creature creature = (Creature) main.World.creatures.get(i);
                path = worldBFS.pathfinder(creature, world);
                if (!path.isEmpty()) {
                    for (int j = 0; j < path.size()-1; j++) {
                        world.moveEntity(path.get(j), path.get(j + 1), creature);
                        WordRender.worldRender(world);
                        System.out.println();
                    }
                    path.clear();
                    targetCount--;

                    if (targetCount < 2) {
                        world.setEntity(world.makeRandomPositionForEntity(), new Grass());
                    }
                } else {
                    // Если пути к текущей траве нет, травоядное перемещается случайно
                    utils.Coordinates newPosition = creature.makeCoordinatesForRandomMovement(creature.coordinates,world);
                    world.moveEntity(creature.coordinates, newPosition, creature);
                    WordRender.worldRender(world);
                    System.out.println();
                }

            }

        }
*/